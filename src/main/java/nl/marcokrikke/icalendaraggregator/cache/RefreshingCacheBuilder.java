package nl.marcokrikke.icalendaraggregator.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Mike Noordermeer
 * @author Erik van Paassen
 * @author Marco Krikke
 */
public class RefreshingCacheBuilder implements InitializingBean {

    private ListeningExecutorService executorService;

    private ScheduledExecutorService scheduledExecutorService;

    private int expireInterval = 3600;


    public <K, V> LoadingCache<K, V> build(int maximumSize, int refreshInterval, final CacheLoader<K, V> loader) {
        final LoadingCache<K, V> cache =
                CacheBuilder.newBuilder().maximumSize(maximumSize).refreshAfterWrite(refreshInterval, TimeUnit.MINUTES)
                        .expireAfterWrite(expireInterval, TimeUnit.MINUTES).build(new CacheLoader<K, V>() {
                    @Override
                    public V load(K key) throws Exception {
                        return loader.load(key);
                    }

                    @Override
                    public ListenableFuture<V> reload(final K key, V oldValue) throws Exception {
                        return executorService.submit(() -> loader.load(key));
                    }
                });

        // Schedule automatic refresh to avoid stale caches.
        // The refresh will always be done in the background (because of the ListenableFuture cacheloader) so it will
        // never slow down client requests.
        scheduledExecutorService.scheduleWithFixedDelay((Runnable) () -> {
            try {
                cache.getAll(cache.asMap().keySet());
            } catch (ExecutionException e) {
                // Ignore any failed executions, since the system might just be slow.
                // Yes, this could lead to a debug mess...
            }
        }, 0, refreshInterval, TimeUnit.MINUTES);

        return cache;
    }

    public void setExpireInterval(int expireInterval) {
        this.expireInterval = expireInterval;
    }

    public void setExecutorService(ListeningExecutorService executorService) {
        this.executorService = executorService;
    }

    public void setScheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = scheduledExecutorService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(executorService);
        Assert.notNull(scheduledExecutorService);
    }
}