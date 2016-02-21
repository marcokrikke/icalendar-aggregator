package nl.marcokrikke.icalendaraggregator.config;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import nl.marcokrikke.icalendaraggregator.cache.RefreshingCacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class CacheConfig {

    @Value("${cache.result.expireIntervalInMinutes}")
    private int expireIntervalInMinutes;

    @Bean
    public ListeningExecutorService executorService() {
        return MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
    }

    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(1);
    }

    @Bean
    public RefreshingCacheBuilder refreshingCacheBuilder() {
        RefreshingCacheBuilder refreshingCacheBuilder = new RefreshingCacheBuilder();
        refreshingCacheBuilder.setExecutorService(executorService());
        refreshingCacheBuilder.setScheduledExecutorService(scheduledExecutorService());
        refreshingCacheBuilder.setExpireInterval(expireIntervalInMinutes);

        return refreshingCacheBuilder;
    }
}
