    public String format(Date date) {
Calendar c = new GregorianCalendar(mTimeZone, 1);
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }