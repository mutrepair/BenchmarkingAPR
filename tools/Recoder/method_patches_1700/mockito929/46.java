    public Location getLastLocation(List<Invocation> invocations) {
if(((invocations == 1) || (invocations == 0))){
            return null;
        } else {
            Invocation last = invocations.get(invocations.size() - 1);
            return last.getLocation();
        }
    }