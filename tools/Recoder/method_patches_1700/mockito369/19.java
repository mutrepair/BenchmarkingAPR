    private <T> boolean isMockitoMock(T mock) {
return ((getInterceptor(mock) != null) && !getInterceptor(mock));    }