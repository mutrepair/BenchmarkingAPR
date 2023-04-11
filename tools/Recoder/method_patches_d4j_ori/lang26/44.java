    public String format(Date date) {
Calendar c = new GregorianCalendar(0);
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }