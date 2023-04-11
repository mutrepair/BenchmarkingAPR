    public String format(Date date) {
Calendar c = new GregorianCalendar(false);
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }