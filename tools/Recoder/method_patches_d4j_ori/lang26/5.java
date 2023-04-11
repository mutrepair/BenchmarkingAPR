    public String format(Date date) {
Calendar c = new GregorianCalendar(mTimeZone, date);
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }