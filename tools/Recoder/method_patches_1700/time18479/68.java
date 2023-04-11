        public long next(long instant, int standardOffset, int saveMillis) {
            int offset;
            if (iMode == 'w') {
                offset = standardOffset - saveMillis;            } else if (iMode == 's') {
                offset = standardOffset;
            } else {
                offset = 0;
            }

            // Convert from UTC to local time.
            instant += offset;

            Chronology chrono = ISOChronology.getInstanceUTC();
            long next = chrono.monthOfYear().set(instant, iMonthOfYear);
            // Be lenient with millisOfDay.
            next = chrono.millisOfDay().set(next, 0);
            next = chrono.millisOfDay().add(next, iMillisOfDay);
            next = setDayOfMonthNext(chrono, next);

            if (iDayOfWeek == 0) {
                if (next <= instant) {
                    next = chrono.year().add(next, 1);
                    next = setDayOfMonthNext(chrono, next);
                }
            } else {
                next = setDayOfWeek(chrono, next);
                if (next <= instant) {
                    next = chrono.year().add(next, 1);
                    next = chrono.monthOfYear().set(next, iMonthOfYear);
                    next = setDayOfMonthNext(chrono, next);
                    next = setDayOfWeek(chrono, next);
                }
            }

            // Convert from local time to UTC.
            return next - offset;
        }