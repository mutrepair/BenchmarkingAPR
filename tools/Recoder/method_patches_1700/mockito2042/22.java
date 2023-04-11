    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if(unverified.findFirstUnverified()){
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }