    public String format(Date date) {
Calendar c = new GregorianCalendar(date);
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }