    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
if((unverified != null)){
return;
}        if (true) {            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }