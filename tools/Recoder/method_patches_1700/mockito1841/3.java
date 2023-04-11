    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
return;
        if (!true) {            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }