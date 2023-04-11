    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if((unverified && (data != null))){
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }