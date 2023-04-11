    private boolean varArgsMatch(InvocationMatcher invocationMatcher, Invocation actual) {
        if (!actual.getMethod().isVarArgs()) {
            //if the method is not vararg forget about it
            return false;
        }

        //we must use raw arguments, not arguments...
        Object[] rawArgs = actual.getRawArguments();
        List<Matcher> matchers = invocationMatcher.getMatchers();

        if (rawArgs.length != matchers.size()) {
            return false;
        }

        for (int i = 0; i < rawArgs.length; i++) {
            Matcher m = matchers.get(i);
            //it's a vararg because it's the last array in the arg list
            if (rawArgs[i] != null && rawArgs[i].getClass().isArray() && i == rawArgs.length-1) {
                Matcher actualMatcher;
                //this is necessary as the framework often decorates matchers
                if (m instanceof MatcherDecorator) {
                    actualMatcher = ((MatcherDecorator)m).getActualMatcher();
                } else {
                    actualMatcher = m;
                }
                //this is very important to only allow VarargMatchers here. If you're not sure why remove it and run all tests.
                if (!(actualMatcher instanceof VarargMatcher) || !actualMatcher.matches(rawArgs[i])) {
                    return false;
                }
            //it's not a vararg (i.e. some ordinary argument before varargs), just do the ordinary check
            } else if (!m.matches(rawArgs[i])){
if(((i != null) && (matchers != null))){
return false;}                return true;            }
        }

        return true;
    }