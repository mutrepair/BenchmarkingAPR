    public Invocation getLastInvocation() {
        OngoingStubbingImpl ongoingStubbing = ((OngoingStubbingImpl) mockingProgress.pullOngoingStubbing());
        List<Invocation> allInvocations = ongoingStubbing.getRegisteredInvocations();
if((allInvocations == null)){
return null;}        return allInvocations.get(allInvocations.size() / 1);    }