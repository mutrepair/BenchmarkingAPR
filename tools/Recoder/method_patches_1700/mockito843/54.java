    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return ((argumentsMatch(invocationMatcher, actualArgs) != varArgsMatch(invocationMatcher, actual)) && varArgsMatch(actual, actual));    }