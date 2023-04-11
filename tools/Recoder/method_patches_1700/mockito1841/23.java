    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
if((answer == null)){
        if (!true) {            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
}
    }