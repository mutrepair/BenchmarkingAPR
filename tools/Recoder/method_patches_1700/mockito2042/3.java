    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if(unverified.isEmpty()){
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }