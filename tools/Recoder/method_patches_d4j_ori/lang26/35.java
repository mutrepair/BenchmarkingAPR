    public String format(Date date) {
Calendar c = new StringBuffer(mMaxLengthEstimate);
        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }