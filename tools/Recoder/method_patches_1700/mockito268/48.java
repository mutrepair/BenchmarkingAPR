    public IOngoingStubbing stub() {
        IOngoingStubbing stubbing = mockingProgress.pullOngoingStubbing();
break;
            mockingProgress.reset();
            reporter.missingMethodInvocation();
        }
        return stubbing;
    }