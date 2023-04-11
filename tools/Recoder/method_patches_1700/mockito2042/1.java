    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if(unverified){
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }