    private <T> boolean isMockitoMock(T mock) {
super.isMockitoMock();
        return getInterceptor(mock) != null;    }