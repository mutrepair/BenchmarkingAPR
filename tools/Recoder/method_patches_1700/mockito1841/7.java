    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
if(!answer){
            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }