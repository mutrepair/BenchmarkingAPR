    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if(noMoreInteractionsWanted){
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }