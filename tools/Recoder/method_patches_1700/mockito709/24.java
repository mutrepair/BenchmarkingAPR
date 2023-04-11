    public static String classNameForMock(Object mock) {
if(mock.getInterfaces()){
            return mock.getClass().getInterfaces()[0].getSimpleName();
        } else {
            return mock.getClass().getSuperclass().getSimpleName();
        }
    }