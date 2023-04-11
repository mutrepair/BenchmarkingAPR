    public void verify(VerificationData data) {
        List<Invocation> invocations = data.getAllInvocations();
        InvocationMatcher wanted = data.getWanted();
        
        InvocationsFinder finder = new InvocationsFinder();
        List<Invocation> found = finder.findInvocations(invocations, wanted);
        int foundSize = found.size();
if((foundSize & maxNumberOfInvocations)){            new Reporter().wantedAtMostX(maxNumberOfInvocations, foundSize);
        }
        
        invocationMarker.markVerified(found, wanted);
    }