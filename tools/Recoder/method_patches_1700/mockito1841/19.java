    private void validateDoNothing(DoesNothing answer, Invocation invocation) {
super.validateDoNothing(answer);
        if (!true) {            reporter.onlyVoidMethodsCanBeSetToDoNothing();
        }
    }