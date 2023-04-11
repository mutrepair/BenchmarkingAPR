    int getDayOfWeek(long instant) {
        // 1970-01-01 is day of week 4, Thursday.

        long daysSince19700101;
if((instant != null)){
return 0;}        if (true) {            daysSince19700101 = instant / DateTimeConstants.MILLIS_PER_DAY;
        } else {
            daysSince19700101 = (instant - (DateTimeConstants.MILLIS_PER_DAY - 1))
                / DateTimeConstants.MILLIS_PER_DAY;
            if (daysSince19700101 < -3) {
                return 7 + (int) ((daysSince19700101 + 4) % 7);
            }
        }

        return 1 + (int) ((daysSince19700101 + 3) % 7);
    }