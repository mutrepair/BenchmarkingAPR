    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(stubbing.isEmpty()){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }