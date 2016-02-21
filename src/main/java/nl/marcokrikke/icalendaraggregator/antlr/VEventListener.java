package nl.marcokrikke.icalendaraggregator.antlr;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class VEventListener extends ICalendarBaseListener {

    private StringBuilder resultBuilder;
    private StringBuilder eventBuilder = new StringBuilder();

    /* Config */
    private Optional<LocalDate> ignoreEventsBefore;
    private boolean removeOrganiser;
    private boolean removeAttendees;

    /* State */
    private boolean parsingEvent;
    private boolean parsingDtStart;
    private boolean skipVevent;


    public VEventListener(StringBuilder resultBuilder, Optional<LocalDate> ignoreEventsBefore, boolean removeOrganiser,
                          boolean removeAttendees) {
        this.resultBuilder = resultBuilder;
        this.ignoreEventsBefore = ignoreEventsBefore;
        this.removeOrganiser = removeOrganiser;
        this.removeAttendees = removeAttendees;
    }

    @Override
    public void enterEventc(ICalendarParser.EventcContext ctx) {
        super.enterEventc(ctx);

        parsingEvent = true;
    }

    @Override
    public void exitEventc(ICalendarParser.EventcContext ctx) {
        super.exitEventc(ctx);

        if (!skipVevent) {
            resultBuilder.append("BEGIN:VEVENT\r\n").append(eventBuilder).append("END:VEVENT\r\n");
        }

        parsingEvent = false;
        skipVevent = false;
        eventBuilder = new StringBuilder();
    }

    @Override
    public void enterDtstart(ICalendarParser.DtstartContext ctx) {
        super.enterDtstart(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }

        parsingDtStart = true;
    }

    @Override
    public void enterDtend(ICalendarParser.DtendContext ctx) {
        super.enterDtend(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }

        parsingDtStart = false;
    }

    @Override
    public void enterDate_time(ICalendarParser.Date_timeContext ctx) {
        super.enterDate_time(ctx);

        // Skip events that occur before a specified date
        if (ignoreEventsBefore.isPresent() && parsingEvent && parsingDtStart) {
            try {
                LocalDate dtStart =
                        LocalDate.parse(ctx.getText().substring(0, 8), DateTimeFormatter.ofPattern("yyyyMMdd"));

                if (dtStart.isBefore(ignoreEventsBefore.get())) {
                    skipVevent = true;
                }
            } catch (DateTimeParseException e) {
                skipVevent = true;
            }
        }
    }

    @Override
    public void enterDate(ICalendarParser.DateContext ctx) {
        super.enterDate(ctx);

        // Skip events that occur before a specified date
        if (ignoreEventsBefore.isPresent() && parsingEvent && parsingDtStart) {
            try {
                LocalDate dtStart =
                        LocalDate.parse(ctx.getText(), DateTimeFormatter.ofPattern("yyyyMMdd"));

                if (dtStart.isBefore(ignoreEventsBefore.get())) {
                    skipVevent = true;
                }
            } catch (DateTimeParseException e) {
                skipVevent = true;
            }
        }
    }

    @Override
    public void enterDtstamp(ICalendarParser.DtstampContext ctx) {
        super.enterDtstamp(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterUid(ICalendarParser.UidContext ctx) {
        super.enterUid(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterCreated(ICalendarParser.CreatedContext ctx) {
        super.enterCreated(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterDescription(ICalendarParser.DescriptionContext ctx) {
        super.enterDescription(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterLast_mod(ICalendarParser.Last_modContext ctx) {
        super.enterLast_mod(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterLocation(ICalendarParser.LocationContext ctx) {
        super.enterLocation(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterSeq(ICalendarParser.SeqContext ctx) {
        super.enterSeq(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterStatus(ICalendarParser.StatusContext ctx) {
        super.enterStatus(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterSummary(ICalendarParser.SummaryContext ctx) {
        super.enterSummary(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterTransp(ICalendarParser.TranspContext ctx) {
        super.enterTransp(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterRecurid(ICalendarParser.RecuridContext ctx) {
        super.enterRecurid(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterRrule(ICalendarParser.RruleContext ctx) {
        super.enterRrule(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterOrganizer(ICalendarParser.OrganizerContext ctx) {
        super.enterOrganizer(ctx);

        if (parsingEvent && !removeOrganiser) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterAttendee(ICalendarParser.AttendeeContext ctx) {
        super.enterAttendee(ctx);

        if (parsingEvent && !removeAttendees) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterExdate(ICalendarParser.ExdateContext ctx) {
        super.enterExdate(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterClazz(ICalendarParser.ClazzContext ctx) {
        super.enterClazz(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterGeo(ICalendarParser.GeoContext ctx) {
        super.enterGeo(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterPriority(ICalendarParser.PriorityContext ctx) {
        super.enterPriority(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterUrl(ICalendarParser.UrlContext ctx) {
        super.enterUrl(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterDuration(ICalendarParser.DurationContext ctx) {
        super.enterDuration(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterCategories(ICalendarParser.CategoriesContext ctx) {
        super.enterCategories(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterComment(ICalendarParser.CommentContext ctx) {
        super.enterComment(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterContact(ICalendarParser.ContactContext ctx) {
        super.enterContact(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterRstatus(ICalendarParser.RstatusContext ctx) {
        super.enterRstatus(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterRelated(ICalendarParser.RelatedContext ctx) {
        super.enterRelated(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterResources(ICalendarParser.ResourcesContext ctx) {
        super.enterResources(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterRdate(ICalendarParser.RdateContext ctx) {
        super.enterRdate(ctx);

        if (parsingEvent) {
            eventBuilder.append(ctx.getText());
        }
    }
}
