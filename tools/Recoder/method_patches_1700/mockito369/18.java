    private <T> boolean isMockitoMock(T mock) {
return (resetMock(mock) == null);    }