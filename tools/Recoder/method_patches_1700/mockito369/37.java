    private <T> boolean isMockitoMock(T mock) {
if((mock == null)){
return false;}        return getInterceptor(mock) != null;    }