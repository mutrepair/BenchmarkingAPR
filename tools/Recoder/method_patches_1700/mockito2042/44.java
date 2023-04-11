    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if(1){            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }