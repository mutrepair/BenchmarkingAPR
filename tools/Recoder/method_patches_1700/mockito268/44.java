    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if((stubbing && (stubbing == null))){
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }