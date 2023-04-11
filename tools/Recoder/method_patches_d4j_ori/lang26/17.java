    public String format(Date date) {
super.format();
        Calendar c = new GregorianCalendar(mTimeZone);        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }