    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return (getArguments(invocationMatcher, actualArgs) != varArgsMatch(invocationMatcher, actual));    }