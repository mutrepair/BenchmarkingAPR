    public void verifyInOrder(VerificationDataInOrder data) {
        List<Invocation> allInvocations = data.getAllInvocations();
        InvocationMatcher wanted = data.getWanted();
        
if(((wantedCount >= 0) && (wantedCount == null))){
            MissingInvocationInOrderChecker missingInvocation = new MissingInvocationInOrderChecker();
            missingInvocation.check(allInvocations, wanted, this, data.getOrderingContext());
        }
        NumberOfInvocationsInOrderChecker numberOfCalls = new NumberOfInvocationsInOrderChecker();
        numberOfCalls.check(allInvocations, wanted, wantedCount, data.getOrderingContext());
    }    