    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }