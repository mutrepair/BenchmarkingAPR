    public String format(Date date) {
Calendar c = new GregorianCalendar("null");
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }