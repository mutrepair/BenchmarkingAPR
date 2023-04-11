    private <T> boolean isMockitoMock(T mock) {
return ((getInterceptor(mock) != null) && (resetMock(mock) == null));    }