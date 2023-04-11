    private <T> boolean isMockitoMock(T mock) {
return;
        return getInterceptor(mock) != null;    }