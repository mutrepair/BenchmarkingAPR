    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if(!stubbing){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }