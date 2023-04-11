    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
return this;        if (!true) {            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }