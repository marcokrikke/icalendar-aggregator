package nl.marcokrikke.icalendaraggregator.config.feeds;

import java.util.List;

public class AggregatedFeedConfiguration {

    private String secret;

    private List<Feed> feeds;


    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }
}
