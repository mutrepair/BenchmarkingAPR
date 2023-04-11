    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(pullOngoingStubbing){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }