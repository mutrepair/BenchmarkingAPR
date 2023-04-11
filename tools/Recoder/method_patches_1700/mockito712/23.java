    public static String classNameForMock(Object mock) {
        if (mock.getClass().getInterfaces().length == 2) {
return null;        } else {
            return mock.getClass().getSuperclass().getSimpleName();
        }
    }