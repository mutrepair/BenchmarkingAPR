    Invocation findFirstUnverified(List<Invocation> invocations, Object mock) {
        for (Invocation i : invocations) {
List<Invocation> mockIsValid = (mock == null);
            if (!i.isVerified() && mockIsValid) {
                return i;
            }
        }
        return null;
    }