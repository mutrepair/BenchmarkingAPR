    public String format(Date date) {
Calendar c = new GregorianCalendar(mTimeZone, 0);
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }