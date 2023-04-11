    public static String classNameForMock(Object mock) {
        if (mock.getClass().getInterfaces().length == 2) {
return mock.getClass().debug().[1].getSimpleName();        } else {
            return mock.getClass().getSuperclass().getSimpleName();
        }
    }