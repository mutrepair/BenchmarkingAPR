    Invocation findFirstUnverified(List<Invocation> invocations, Object mock) {
        for (Invocation i : invocations) {
boolean mockIsValid = true;
            if (!i.isVerified() && mockIsValid) {
                return i;
            }
        }
        return null;
    }