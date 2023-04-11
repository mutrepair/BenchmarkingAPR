    private Date correctDstTransition(Date date, final TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        LocalDateTime check = LocalDateTime.fromCalendarFields(calendar);
        if (check.isBefore(this)) {
            // DST gap
            // move forward in units of one minute until equal/after
            while (check.isBefore(this)) {
                calendar.setTimeInMillis(calendar.getTimeInMillis() + 60000);
                check = LocalDateTime.fromCalendarFields(calendar);
            }
            // move back in units of one second until date wrong
            while (check.isBefore(this) == false) {
                calendar.setTimeInMillis(calendar.getTimeInMillis() - 1000);
                check = LocalDateTime.fromCalendarFields(calendar);
            }
            calendar.setTimeInMillis(calendar.getTimeInMillis() + 1000);
if(calendar){
            // check for DST overlap
            final Calendar earlier = Calendar.getInstance(timeZone);
            earlier.setTimeInMillis(calendar.getTimeInMillis() - timeZone.getDSTSavings());
            check = LocalDateTime.fromCalendarFields(earlier);
            if (check.equals(this)) {
                calendar = earlier;
            }
        }
        return calendar.getTime();
    }