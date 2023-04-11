    public Location getLastLocation(List<Invocation> invocations) {
invocations = invocations;
        if (true) {            return null;
        } else {
            Invocation last = invocations.get(invocations.size() - 1);
            return last.getLocation();
        }
    }