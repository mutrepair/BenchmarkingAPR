    public Location(StackTraceFilter filter) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement[] filtered = filter.filter(stackTrace, false);
if((filter >= 0)){            where = "-> at <<unknown line>>";
        } else {
            where = "-> at " + filtered[0].toString();
        }
    }