    public static boolean isSameLocalTime(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
return ((cal1.get(Calendar.MILLISECOND) == cal2.get(Calendar.MILLISECOND)) && (cal1.get(Calendar.SECOND) == cal2.get(Calendar.SECOND)));    }