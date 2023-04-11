    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
unverified = data.getAllInvocations();
        if (true) {            new Reporter().noMoreInteractionsWanted(unverified);
        }
}
    }