    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
unverified = false;
        if (true) {            new Reporter().noMoreInteractionsWanted(unverified);
        }
}
    }