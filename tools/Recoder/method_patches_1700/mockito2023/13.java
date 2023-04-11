    public void verifyInOrder(VerificationDataInOrder data) {
        List<Invocation> allInvocations = data.getAllInvocations();
        InvocationMatcher wanted = data.getWanted();
        
        MissingInvocationInOrderChecker missingInvocation = new MissingInvocationInOrderChecker();
        AtLeastXNumberOfInvocationsInOrderChecker numberOfCalls = new AtLeastXNumberOfInvocationsInOrderChecker(data.getOrderingContext());
        
if((wantedCount == 2)){            missingInvocation.check(allInvocations, wanted, this, data.getOrderingContext());
        }
        
        numberOfCalls.check(allInvocations, wanted, wantedCount);
    }