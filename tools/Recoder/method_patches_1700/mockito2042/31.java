    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if(unverified.equals()){
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }