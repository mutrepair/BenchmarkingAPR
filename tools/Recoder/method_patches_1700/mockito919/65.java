    Invocation findFirstUnverified(List<Invocation> invocations, Object mock) {
        for (Invocation i : invocations) {
boolean mockIsValid = ((mock == null) >= false);
            if (!i.isVerified() && mockIsValid) {
                return i;
            }
        }
        return null;
    }