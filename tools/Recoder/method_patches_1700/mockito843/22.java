    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return (argumentsMatch(invocationMatcher, invocationMatcher) != varArgsMatch(invocationMatcher, actual));    }