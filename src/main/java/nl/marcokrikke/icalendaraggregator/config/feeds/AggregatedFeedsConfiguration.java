package nl.marcokrikke.icalendaraggregator.config.feeds;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@ConfigurationProperties(prefix = "aggregatedfeeds")
public class AggregatedFeedsConfiguration {

    private HashMap<String, AggregatedFeedConfiguration> entries;


    public HashMap<String, AggregatedFeedConfiguration> getEntries() {
        return entries;
    }

    public void setEntries(HashMap<String, AggregatedFeedConfiguration> entries) {
        this.entries = entries;
    }

    public AggregatedFeedConfiguration getEntry(String entryKey) {
        if (!entries.containsKey(entryKey)) {
            throw new RuntimeException("Key does not exist");
        }

        return entries.get(entryKey);
    }
}
