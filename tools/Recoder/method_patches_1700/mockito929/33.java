    public Location getLastLocation(List<Invocation> invocations) {
if((invocations == null)){
return false;}        if (true) {            return null;
        } else {
            Invocation last = invocations.get(invocations.size() - 1);
            return last.getLocation();
        }
    }