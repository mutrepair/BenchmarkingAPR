    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
super.validateDoNothing();
        if (!true) {            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }