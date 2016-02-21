package nl.marcokrikke.icalendaraggregator;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import nl.marcokrikke.icalendaraggregator.antlr.ICalendarLexer;
import nl.marcokrikke.icalendaraggregator.antlr.ICalendarParser;
import nl.marcokrikke.icalendaraggregator.antlr.VEventListener;
import nl.marcokrikke.icalendaraggregator.config.feeds.Feed;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.UnbufferedTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

@Service
public class FeedServiceImpl implements FeedService {

    private Logger logger = LoggerFactory.getLogger(FeedServiceImpl.class);

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public String aggregateFeeds(List<Feed> feeds) {
        logger.info("Aggregating {} feeds", feeds.size());

        StringBuilder resultBuilder = new StringBuilder();

        addStart(resultBuilder);
        addBodyStart(resultBuilder);

        for (Feed feed : feeds) {
            collectEvents(feed, resultBuilder);
        }

        addEnd(resultBuilder);

        logger.info("Aggregation finished");

        return resultBuilder.toString();
    }

    private void addStart(StringBuilder stringBuilder) {
        stringBuilder.append("BEGIN:VCALENDAR\r\n");
    }

    private void addBodyStart(StringBuilder stringBuilder) {
        Optional<String> content = getContent("classpath:bodyStart.ics");

        if (content.isPresent()) {
            stringBuilder.append(content.get()).append("\r\n");
        }
    }

    private void addEnd(StringBuilder stringBuilder) {
        stringBuilder.append("END:VCALENDAR");
    }

    private void collectEvents(Feed feed, StringBuilder stringBuilder) {
        Optional<String> rawFeedContent = getContent(feed.getUrl());

        if (!rawFeedContent.isPresent()) {
            return;
        }

        String content = rawFeedContent.get();

        // Remove empty lines, our grammar doesn't like that
        content = content.replaceAll("(?m)^[ \t]*\r?\n", "");

        ANTLRInputStream input = new ANTLRInputStream(content);

        ICalendarLexer lex = new ICalendarLexer(input);

        UnbufferedTokenStream tokens = new UnbufferedTokenStream(lex);
        ICalendarParser parser = new ICalendarParser(tokens);

        ICalendarParser.IcalobjectContext tree = parser.icalobject();

        // Custom listener for our parser. Adds content to a StringBuilder.
        StringBuilder vEvents = new StringBuilder();
        VEventListener listener = new VEventListener(vEvents, feed.getIgnoreEventsBefore(), feed.isRemoveOrganiser(),
                feed.isRemoveAttendees());

        // Walk the tree and use the listener
        ParseTreeWalker.DEFAULT.walk(listener, tree);

        stringBuilder.append(vEvents);
    }

    private Optional<String> getContent(String resource) {
        try {
            Resource r = resourceLoader.getResource(resource);

            return Optional.of(Resources.toString(r.getURL(), Charsets.UTF_8));
        } catch (MalformedURLException e) {
            logger.error("Could not load feed", e);
        } catch (IOException e) {
            logger.error("Could not load feed", e);
        }

        return Optional.empty();
    }
}
