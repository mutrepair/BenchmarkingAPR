    public String format(Date date) {
Calendar c = new GregorianCalendar(mMaxLengthEstimate);
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }