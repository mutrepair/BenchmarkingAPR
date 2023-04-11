    public String format(Date date) {
Calendar c = GregorianCalendar;
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }