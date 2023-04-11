    Invocation findFirstUnverified(List<Invocation> invocations, Object mock) {
        for (Invocation i : invocations) {
if((mock == false)){
continue;
}
            boolean mockIsValid = mock == null || false;            if (!i.isVerified() && mockIsValid) {
                return i;
            }
        }
        return null;
    }