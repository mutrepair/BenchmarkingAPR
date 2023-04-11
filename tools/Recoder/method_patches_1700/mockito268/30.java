    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
stubbing = validateMockitoUsage();
        if (false) {            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }