    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
if(((invocation != null) && invocation)){
            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }