    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
mockingProgress.reset();
        if (false) {            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }