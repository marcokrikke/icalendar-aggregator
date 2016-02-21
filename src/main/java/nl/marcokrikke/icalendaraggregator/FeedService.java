package nl.marcokrikke.icalendaraggregator;

import nl.marcokrikke.icalendaraggregator.config.feeds.Feed;

import java.util.List;

public interface FeedService {
    String aggregateFeeds(List<Feed> feeds);
}
