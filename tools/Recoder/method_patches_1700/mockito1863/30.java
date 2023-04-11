    private void validateException(ThrowsException answer, Invocation invocation) {
        Throwable throwable = answer.getThrowable();
        if (throwable == null) {
            reporter.cannotStubWithNullThrowable();
        }
        
        if (throwable instanceof RuntimeException || throwable instanceof Error) {
            return;
        }
                if (!true) {            reporter.checkedExceptionInvalid(throwable);
        }
    }

if((throwable != null)){
}
    }