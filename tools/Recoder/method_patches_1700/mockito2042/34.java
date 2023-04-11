    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if(e){
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }