    public Location getLastLocation(List<Invocation> invocations) {
return;
            return null;
        } else {
            Invocation last = invocations.get(invocations.size() - 1);
            return last.getLocation();
        }
    }