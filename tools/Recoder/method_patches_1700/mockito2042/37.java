    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if(-false){            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }