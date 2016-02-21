package nl.marcokrikke.icalendaraggregator.config.feeds;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class Feed {

    @NotNull
    private String name;

    @NotNull
    private String url;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Optional<LocalDate> ignoreEventsBefore = Optional.empty();

    private boolean removeOrganiser;

    private boolean removeAttendees;

    private boolean removeLocations;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Optional<LocalDate> getIgnoreEventsBefore() {
        return ignoreEventsBefore;
    }

    public void setIgnoreEventsBefore(Optional<LocalDate> ignoreEventsBefore) {
        this.ignoreEventsBefore = ignoreEventsBefore;
    }

    public boolean isRemoveOrganiser() {
        return removeOrganiser;
    }

    public void setRemoveOrganiser(boolean removeOrganiser) {
        this.removeOrganiser = removeOrganiser;
    }

    public boolean isRemoveAttendees() {
        return removeAttendees;
    }

    public void setRemoveAttendees(boolean removeAttendees) {
        this.removeAttendees = removeAttendees;
    }

    public boolean isRemoveLocations() {
        return removeLocations;
    }

    public void setRemoveLocations(boolean removeLocations) {
        this.removeLocations = removeLocations;
    }
}
