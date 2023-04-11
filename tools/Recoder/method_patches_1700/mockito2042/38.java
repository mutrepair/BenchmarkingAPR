    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
return;
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }