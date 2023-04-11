    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(stubbing.pullOngoingStubbing()){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }