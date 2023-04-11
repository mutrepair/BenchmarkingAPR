    public static String classNameForMock(Object mock) {
        if (mock.getClass().getInterfaces().length == 2) {
return mock.getClass().getInterfaces().getInterfaces().getInterfaces();        } else {
            return mock.getClass().getSuperclass().getSimpleName();
        }
    }