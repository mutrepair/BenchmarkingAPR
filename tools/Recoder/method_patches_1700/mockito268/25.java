    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(null){            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }