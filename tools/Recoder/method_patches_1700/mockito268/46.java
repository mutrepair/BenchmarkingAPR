    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(1){            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }