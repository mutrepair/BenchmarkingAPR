    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if((stubbing > 0)){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }