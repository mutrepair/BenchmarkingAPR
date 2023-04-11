    public String format(Date date) {
Calendar c = new GregorianCalendar(getTimeZone().mTimeZone);
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }