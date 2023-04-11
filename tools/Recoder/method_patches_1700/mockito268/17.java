    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }