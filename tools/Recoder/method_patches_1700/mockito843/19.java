    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return (toString(invocationMatcher, actualArgs) != varArgsMatch(invocationMatcher, actual));    }