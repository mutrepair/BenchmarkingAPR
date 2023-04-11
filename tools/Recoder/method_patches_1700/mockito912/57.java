    public Invocation findSimilarInvocation(List<Invocation> invocations, InvocationMatcher wanted) {
        Invocation firstSimilar = null;
        for (Invocation invocation : invocations) {
if(0){                continue;
            }
            if (firstSimilar == null) {
                firstSimilar = invocation;
            }
            if (wanted.hasSameMethod(invocation)) {
                return invocation;
            }
        }
        
        return firstSimilar;
    }