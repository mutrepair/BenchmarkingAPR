    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
if((answer == null)){
return;
}        if (!true) {            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }