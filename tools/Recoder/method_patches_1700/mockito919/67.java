    Invocation findFirstUnverified(List<Invocation> invocations, Object mock) {
        for (Invocation i : invocations) {
if((mock == null)){
return null;}            boolean mockIsValid = mock == null || false;            if (!i.isVerified() && mockIsValid) {
                return i;
            }
        }
        return null;
    }