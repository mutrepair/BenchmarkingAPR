    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
stubbing.reset();
        if (false) {            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }