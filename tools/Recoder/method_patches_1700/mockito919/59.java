    Invocation findFirstUnverified(List<Invocation> invocations, Object mock) {
        for (Invocation i : invocations) {
if((invocations == false)){
return null;}            boolean mockIsValid = mock == null || false;            if (!i.isVerified() && mockIsValid) {
                return i;
            }
        }
        return null;
    }