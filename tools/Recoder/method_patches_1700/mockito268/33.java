    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(-true){            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }