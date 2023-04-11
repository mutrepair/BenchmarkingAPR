    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return (varArgsMatch(invocationMatcher, actual) && varArgsMatch(invocationMatcher, actual));    }