package nl.marcokrikke.icalendaraggregator;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import nl.marcokrikke.icalendaraggregator.cache.RefreshingCacheBuilder;
import nl.marcokrikke.icalendaraggregator.config.feeds.AggregatedFeedConfiguration;
import nl.marcokrikke.icalendaraggregator.config.feeds.AggregatedFeedsConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController implements InitializingBean {

    @Autowired
    private AggregatedFeedsConfiguration feedConfiguration;

    @Autowired
    private FeedService feedService;

    @Autowired
    private RefreshingCacheBuilder cacheBuilder;

    private LoadingCache<String, String> cache;

    @Value("${cache.result.refreshIntervalInMinutes}")
    private int refreshInterval;


    @RequestMapping(value = "/feed/{entry}/{secret}", produces = "text/calendar")
    public ResponseEntity<String> getFeed(@PathVariable String entry, @PathVariable String secret) {
        AggregatedFeedConfiguration configuration = feedConfiguration.getEntry(entry);

        if (!secret.equals(configuration.getSecret())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(cache.getUnchecked(entry), HttpStatus.OK);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cache = cacheBuilder.build(1, refreshInterval, new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                AggregatedFeedConfiguration configuration = feedConfiguration.getEntry(key);

                return feedService.aggregateFeeds(configuration.getFeeds());
            }
        });
    }
}
