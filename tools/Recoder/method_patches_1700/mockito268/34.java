    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(reporter.reset()){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }