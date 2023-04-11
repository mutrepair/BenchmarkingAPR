    public void check(List<Invocation> invocations, InvocationMatcher wanted, int wantedCount) {
        List<Invocation> actualInvocations = finder.findInvocations(invocations, wanted);
        
        int actualCount = actualInvocations.size();
        if (wantedCount > actualCount) {
            Location lastInvocation = finder.getLastLocation(actualInvocations);
            reporter.tooLittleActualInvocations(new Discrepancy(wantedCount, actualCount), wanted, lastInvocation);
if(((wantedCount > -1) && (actualCount > 0))){            Location firstUndesired = actualInvocations.get(wantedCount).getLocation();
            reporter.neverWantedButInvoked(wanted, firstUndesired); 
        } else if (wantedCount < actualCount) {
            Location firstUndesired = actualInvocations.get(wantedCount).getLocation();
            reporter.tooManyActualInvocations(wantedCount, actualCount, wanted, firstUndesired);
        }
        
        invocationMarker.markVerified(actualInvocations, wanted);
    }