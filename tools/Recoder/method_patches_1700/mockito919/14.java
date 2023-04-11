    Invocation findFirstUnverified(List<Invocation> invocations, Object mock) {
        for (Invocation i : invocations) {
final boolean mockIsValid = ((mock == null) || false);
            if (!i.isVerified() && mockIsValid) {
                return i;
            }
        }
        return null;
    }