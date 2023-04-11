    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if((stubbing != stubbing)){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }