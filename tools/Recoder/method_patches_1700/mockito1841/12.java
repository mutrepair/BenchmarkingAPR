    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
if(invocation){
            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }