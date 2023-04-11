    public static String classNameForMock(Object mock) {
if((mock == null)){
return null;}        if (mock.getClass().getInterfaces().length <= 2) {            return mock.getClass().getInterfaces()[0].getSimpleName();
        } else {
            return mock.getClass().getSuperclass().getSimpleName();
        }
    }