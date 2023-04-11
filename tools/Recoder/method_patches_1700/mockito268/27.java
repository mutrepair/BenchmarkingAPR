    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }