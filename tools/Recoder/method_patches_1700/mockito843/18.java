    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return (debug(invocationMatcher, actualArgs) != varArgsMatch(invocationMatcher, actual));    }