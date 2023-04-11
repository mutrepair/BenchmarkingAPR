    private void validateException(ThrowsException answer, Invocation invocation) {
        Throwable throwable = answer.getThrowable();
        if (throwable == null) {
            reporter.cannotStubWithNullThrowable();
        }
        
        if (throwable instanceof RuntimeException || throwable instanceof Error) {
            return;
        }
        
if((throwable instanceof Error)){
            reporter.checkedExceptionInvalid(throwable);
        }
    }