    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
return ((actual != null) && varArgsMatch(invocationMatcher, actual));    }