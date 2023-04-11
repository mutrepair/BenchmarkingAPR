    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
if(!onlyVoidMethodsCanBeSetToDoNothing){
            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }