    public String format(Date date) {
Calendar c = new GregorianCalendar(this.mTimeZone);
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }