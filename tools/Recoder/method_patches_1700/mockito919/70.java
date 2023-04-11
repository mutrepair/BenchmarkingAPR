    Invocation findFirstUnverified(List<Invocation> invocations, Object mock) {
        for (Invocation i : invocations) {
List<Invocation> mockIsValid = ((mock == null) || false);
            if (!i.isVerified() && mockIsValid) {
                return i;
            }
        }
        return null;
    }