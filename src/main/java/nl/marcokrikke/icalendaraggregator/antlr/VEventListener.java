package nl.marcokrikke.icalendaraggregator.antlr;

public class VEventListener extends ICalendarBaseListener {

    private StringBuilder stringBuilder;

    private boolean parsingEvent;

    public VEventListener(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }


    @Override
    public void enterEventc(ICalendarParser.EventcContext ctx) {
        super.enterEventc(ctx);

        parsingEvent = true;

        stringBuilder.append("BEGIN:VEVENT\r\n");
    }

    @Override
    public void exitEventc(ICalendarParser.EventcContext ctx) {
        super.exitEventc(ctx);

        parsingEvent = false;

        stringBuilder.append("END:VEVENT\r\n");
    }

    @Override
    public void enterDtstart(ICalendarParser.DtstartContext ctx) {
        super.enterDtstart(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterDtend(ICalendarParser.DtendContext ctx) {
        super.enterDtend(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterDtstamp(ICalendarParser.DtstampContext ctx) {
        super.enterDtstamp(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterUid(ICalendarParser.UidContext ctx) {
        super.enterUid(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterCreated(ICalendarParser.CreatedContext ctx) {
        super.enterCreated(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterDescription(ICalendarParser.DescriptionContext ctx) {
        super.enterDescription(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterLast_mod(ICalendarParser.Last_modContext ctx) {
        super.enterLast_mod(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterLocation(ICalendarParser.LocationContext ctx) {
        super.enterLocation(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterSeq(ICalendarParser.SeqContext ctx) {
        super.enterSeq(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterStatus(ICalendarParser.StatusContext ctx) {
        super.enterStatus(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterSummary(ICalendarParser.SummaryContext ctx) {
        super.enterSummary(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterTransp(ICalendarParser.TranspContext ctx) {
        super.enterTransp(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterRecurid(ICalendarParser.RecuridContext ctx) {
        super.enterRecurid(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterRrule(ICalendarParser.RruleContext ctx) {
        super.enterRrule(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterOrganizer(ICalendarParser.OrganizerContext ctx) {
        super.enterOrganizer(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterAttendee(ICalendarParser.AttendeeContext ctx) {
        super.enterAttendee(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterExdate(ICalendarParser.ExdateContext ctx) {
        super.enterExdate(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterClazz(ICalendarParser.ClazzContext ctx) {
        super.enterClazz(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterGeo(ICalendarParser.GeoContext ctx) {
        super.enterGeo(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterPriority(ICalendarParser.PriorityContext ctx) {
        super.enterPriority(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterUrl(ICalendarParser.UrlContext ctx) {
        super.enterUrl(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterDuration(ICalendarParser.DurationContext ctx) {
        super.enterDuration(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterCategories(ICalendarParser.CategoriesContext ctx) {
        super.enterCategories(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterComment(ICalendarParser.CommentContext ctx) {
        super.enterComment(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterContact(ICalendarParser.ContactContext ctx) {
        super.enterContact(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterRstatus(ICalendarParser.RstatusContext ctx) {
        super.enterRstatus(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterRelated(ICalendarParser.RelatedContext ctx) {
        super.enterRelated(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterResources(ICalendarParser.ResourcesContext ctx) {
        super.enterResources(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }

    @Override
    public void enterRdate(ICalendarParser.RdateContext ctx) {
        super.enterRdate(ctx);

        if (parsingEvent) {
            stringBuilder.append(ctx.getText());
        }
    }
}
