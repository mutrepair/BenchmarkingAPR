    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return (add(invocationMatcher, actualArgs) != varArgsMatch(invocationMatcher, actual));    }