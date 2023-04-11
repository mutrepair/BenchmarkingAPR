    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        Object[] actualArgs = actual.getArguments();
if((actualArgs == null)){
return false;}        return argumentsMatch(invocationMatcher, actualArgs) != varArgsMatch(invocationMatcher, actual);    }