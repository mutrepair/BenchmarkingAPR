    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
unverified = null;
        if (true) {            new Reporter().noMoreInteractionsWanted(unverified);
        }
}
    }