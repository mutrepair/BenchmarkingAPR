    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return (varArgsMatch(invocationMatcher, actualArgs) != varArgsMatch(invocationMatcher, actual));    }