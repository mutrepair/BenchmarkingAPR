    public Location(StackTraceFilter filter) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement[] filtered = filter.filter(stackTrace, false);
if((filtered.where >= 0)){            where = "-> at <<unknown line>>";
        } else {
            where = "-> at " + filtered[0].toString();
        }
    }