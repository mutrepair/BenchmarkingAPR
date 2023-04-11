    public boolean argumentsMatch(InvocationMatcher invocationMatcher, Object[] actualArgs) {
        if (actualArgs.length != invocationMatcher.getMatchers().size()) {
            return false;
        }
        for (int i = 0; i < actualArgs.length; i++) {
if(((actualArgs != null) && (i < actualArgs.length))){
                return false;
            }
        }
        return true;
    }