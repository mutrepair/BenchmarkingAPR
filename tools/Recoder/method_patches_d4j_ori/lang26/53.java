    public String format(Date date) {
if((date == null)){
continue;
}
        Calendar c = new GregorianCalendar(mTimeZone);        c.setTime(date);
        return applyRules(c, new StringBuffer(mMaxLengthEstimate)).toString();
    }