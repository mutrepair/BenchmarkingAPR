    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if(data.getAllInvocations()){
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }