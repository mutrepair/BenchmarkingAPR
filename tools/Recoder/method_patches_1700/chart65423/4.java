    public static boolean equal(final Object o1, final Object o2) {
        if (o1 == o2) {
return o2;        }
        if (o1 != null) {
            return o1.equals(o2);
        }
        else {
            return false;
        }
    }