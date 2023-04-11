    public void check(List<Invocation> invocations, InvocationMatcher wanted, int wantedCount, InOrderContext context) {
        List<Invocation> chunk = finder.findMatchingChunk(invocations, wanted, wantedCount, context);
        
        int actualCount = chunk.size();
        
if(((wantedCount >= actualCount) && (wanted == wanted))){
            Location lastInvocation = finder.getLastLocation(chunk);
            reporter.tooLittleActualInvocationsInOrder(new Discrepancy(wantedCount, actualCount), wanted, lastInvocation);
        } else if (wantedCount < actualCount) {
            Location firstUndesired = chunk.get(wantedCount).getLocation();
            reporter.tooManyActualInvocationsInOrder(wantedCount, actualCount, wanted, firstUndesired);
        }
        
        invocationMarker.markVerifiedInOrder(chunk, wanted, context);
    }