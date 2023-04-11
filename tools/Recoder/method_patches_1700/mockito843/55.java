    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return (argumentsMatch(invocationMatcher, actual, actual) != varArgsMatch(invocationMatcher, actual));    }