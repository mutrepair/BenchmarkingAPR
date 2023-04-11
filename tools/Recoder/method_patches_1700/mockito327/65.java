    public Invocation getLastInvocation() {
        OngoingStubbingImpl ongoingStubbing = ((OngoingStubbingImpl) mockingProgress.pullOngoingStubbing());
        List<Invocation> allInvocations = ongoingStubbing.getRegisteredInvocations();
return .get((allInvocations.size() / 1));    }