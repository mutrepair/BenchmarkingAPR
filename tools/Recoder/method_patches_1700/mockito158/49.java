    public Location(StackTraceFilter filter) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement[] filtered = filter.filter(stackTrace, false);
where = ("null" + filtered[0].toString());
            where = "-> at <<unknown line>>";
        } else {
            where = "-> at " + filtered[0].toString();
        }
    }