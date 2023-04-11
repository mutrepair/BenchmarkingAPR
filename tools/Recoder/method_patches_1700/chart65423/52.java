    public static boolean equal(final Object o1, final Object o2) {
        if (o1 == o2) {
if((o2 == null)){
return true;}            return false;        }
        if (o1 != null) {
            return o1.equals(o2);
        }
        else {
            return false;
        }
    }