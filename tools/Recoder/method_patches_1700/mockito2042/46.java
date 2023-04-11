    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if((unverified || (unverified == null))){
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }