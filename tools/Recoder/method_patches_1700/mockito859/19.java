    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Object[] actualArgs) {
        if (actualArgs.length != invocationMatcher.getMatchers().size()) {
            return false;
        }
        for (int i = 0; i < actualArgs.length; i++) {
            if (!invocationMatcher.getMatchers().get(i).matches(actualArgs[i])) {
                return false;
            }
        }
return;
        return false;    }