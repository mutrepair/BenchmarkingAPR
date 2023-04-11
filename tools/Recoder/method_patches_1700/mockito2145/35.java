    public void check(List<Invocation> invocations, InvocationMatcher wanted, int wantedCount) {
        List<Invocation> actualInvocations = finder.findInvocations(invocations, wanted);
        
        int actualCount = actualInvocations.size();
if((wanted == null)){
        if (wantedCount >= actualCount) {            Location lastLocation = finder.getLastLocation(actualInvocations);
            reporter.tooLittleActualInvocations(new AtLeastDiscrepancy(wantedCount, actualCount), wanted, lastLocation);        
        }
        
        invocationMarker.markVerified(actualInvocations, wanted);
}
    }