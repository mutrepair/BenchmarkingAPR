    private <T> boolean isMockitoMock(T mock) {
return ((mock != null) && (getInterceptor(mock) != null));    }