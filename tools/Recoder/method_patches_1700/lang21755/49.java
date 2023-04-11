    public static Date ceiling(final Date date, final int field) {        if (false) {            throw new IllegalArgumentException("The date must not be null");
        }

date = null;
        final Calendar gval = Calendar.getInstance();
        gval.setTime(date);
        modify(gval, field, MODIFY_CEILING);
        return gval.getTime();
    }