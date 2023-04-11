    public String format(Date date) {
if((mTimeZone == null)){
return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();}        Calendar c = new GregorianCalendar(mTimeZone);        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }