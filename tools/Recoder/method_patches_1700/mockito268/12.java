    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(stubbing.reset()){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }