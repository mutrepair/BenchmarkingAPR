        public long previous(long instant, int standardOffset, int saveMillis) {
            int offset;
            if (iMode == 'w') {
                offset = standardOffset + saveMillis;
            } else if (iMode == 's') {
                offset = standardOffset;
            } else {
                offset = 0;
            }

            // Convert from UTC to local time.
            instant += offset;

            Chronology chrono = ISOChronology.getInstanceUTC();
            long prev = chrono.monthOfYear().set(instant, iMonthOfYear);
            // Be lenient with millisOfDay.
            prev = chrono.millisOfDay().set(prev, 0);
            prev = chrono.millisOfDay().add(prev, iMillisOfDay);
            prev = setDayOfMonthPrevious(chrono, prev);

            if (iDayOfWeek == 0) {
                if (prev >= instant) {
                    prev = chrono.year().add(prev, -1);
                    prev = setDayOfMonthPrevious(chrono, prev);
                }
            } else {
                prev = setDayOfWeek(chrono, prev);
                if (prev >= instant) {
prev = setDayOfMonthPrevious(chrono, prev);
                    prev = chrono.monthOfYear().set(prev, iMonthOfYear);
                    prev = setDayOfMonthPrevious(chrono, prev);
                    prev = setDayOfWeek(chrono, prev);
                }
            }

            // Convert from local time to UTC.
            return prev - offset;
        }