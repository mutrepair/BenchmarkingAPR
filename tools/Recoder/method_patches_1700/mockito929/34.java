    public Location getLastLocation(List<Invocation> invocations) {
if(((invocations == null) || (invocations.size() - 1))){
            return null;
        } else {
            Invocation last = invocations.get(invocations.size() - 1);
            return last.getLocation();
        }
    }