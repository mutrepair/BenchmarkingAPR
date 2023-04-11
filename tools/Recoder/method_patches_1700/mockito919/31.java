    Invocation findFirstUnverified(List<Invocation> invocations, Object mock) {
        for (Invocation i : invocations) {
boolean mockIsValid = ((mock == null) || "null");
            if (!i.isVerified() && mockIsValid) {
                return i;
            }
        }
        return null;
    }