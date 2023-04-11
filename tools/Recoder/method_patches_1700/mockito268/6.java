    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(reset){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }