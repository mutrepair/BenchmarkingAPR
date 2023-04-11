    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
if((stubbing != null)){
        if (false) {            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
}
    }