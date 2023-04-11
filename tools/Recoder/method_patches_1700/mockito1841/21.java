    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
if(answer){
return;
}        if (!true) {            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }