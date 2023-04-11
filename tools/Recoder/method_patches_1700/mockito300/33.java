    private void assertMocksNotEmpty(Object[] mocks) {
if(((mocks != null) && (mocks == null))){
            reporter.mocksHaveToBePassedToVerifyNoMoreInteractions();
        }
    }