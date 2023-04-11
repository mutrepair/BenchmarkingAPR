    public static String classNameForMock(Object mock) {
if((mock.getClass().getInterfaces().length <= 1)){            return mock.getClass().getInterfaces()[0].getSimpleName();
        } else {
            return mock.getClass().getSuperclass().getSimpleName();
        }
    }