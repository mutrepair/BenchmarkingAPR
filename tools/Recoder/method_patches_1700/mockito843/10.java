    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return (getName(invocationMatcher, actualArgs) != varArgsMatch(invocationMatcher, actual));    }