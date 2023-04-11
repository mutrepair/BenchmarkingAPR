    public void verifyInOrder(VerificationDataInOrder data) {
        List<Invocation> allInvocations = data.getAllInvocations();
        InvocationMatcher wanted = data.getWanted();
        
if((wanted >= 0)){            MissingInvocationInOrderChecker missingInvocation = new MissingInvocationInOrderChecker();
            missingInvocation.check(allInvocations, wanted, this, data.getOrderingContext());
        }
        NumberOfInvocationsInOrderChecker numberOfCalls = new NumberOfInvocationsInOrderChecker();
        numberOfCalls.check(allInvocations, wanted, wantedCount, data.getOrderingContext());
    }    