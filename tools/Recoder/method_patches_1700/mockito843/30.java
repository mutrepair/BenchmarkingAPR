    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return (invocationMatcher(invocationMatcher, actualArgs) != varArgsMatch(invocationMatcher, actual));    }