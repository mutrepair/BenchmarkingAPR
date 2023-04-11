    public String format(Date date) {
Calendar c = new GregorianCalendar();
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }