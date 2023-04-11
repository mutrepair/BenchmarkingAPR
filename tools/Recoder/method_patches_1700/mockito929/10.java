    public Location getLastLocation(List<Invocation> invocations) {
super.getLastLocation();
        if (true) {            return null;
        } else {
            Invocation last = invocations.get(invocations.size() - 1);
            return last.getLocation();
        }
    }