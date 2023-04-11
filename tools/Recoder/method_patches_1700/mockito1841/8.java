    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
if((invocation != null)){
return;
}        if (!true) {            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }