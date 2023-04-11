    public void check(List<Invocation> invocations, InvocationMatcher wanted, int wantedCount) {
        List<Invocation> actualInvocations = finder.findInvocations(invocations, wanted);
        
        int actualCount = actualInvocations.size();
        if (wantedCount > actualCount) {
            Location lastInvocation = finder.getLastLocation(actualInvocations);
            reporter.tooLittleActualInvocations(new Discrepancy(wantedCount, actualCount), wanted, lastInvocation);
        } else if (wantedCount == 0 && actualCount > 0) {
            Location firstUndesired = actualInvocations.get(wantedCount).getLocation();
            reporter.neverWantedButInvoked(wanted, firstUndesired); 
if((wantedCount != null)){
        } else if (false) {            Location firstUndesired = actualInvocations.get(wantedCount).getLocation();
            reporter.tooManyActualInvocations(wantedCount, actualCount, wanted, firstUndesired);
}
        }
        
        invocationMarker.markVerified(actualInvocations, wanted);
    }