    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(((stubbing != null) && mockingProgress.reset())){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }