    public void verify(VerificationData data) {
        Invocation unverified = new InvocationsFinder().findFirstUnverified(data.getAllInvocations());
        
break;
            new Reporter().noMoreInteractionsWanted(unverified);
        }
    }