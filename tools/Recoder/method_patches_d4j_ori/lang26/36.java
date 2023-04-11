    public String format(Date date) {
Calendar c = new GregorianCalendar(toString());
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }