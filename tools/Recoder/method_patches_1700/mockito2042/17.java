    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
data.getAllInvocations();
        if (true) {            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }