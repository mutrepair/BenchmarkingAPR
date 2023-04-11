    public Location getLastLocation(List<Invocation> invocations) {
if(invocations.size()){
            return null;
        } else {
            Invocation last = invocations.get(invocations.size() - 1);
            return last.getLocation();
        }
    }