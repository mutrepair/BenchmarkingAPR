    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
return null;        if (false) {            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }