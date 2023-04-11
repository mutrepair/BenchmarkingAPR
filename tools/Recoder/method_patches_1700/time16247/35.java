    private static void time(
        DateTimeFormatterBuilder bld,
        Collection<DateTimeFieldType> fields,
        boolean extended,
        boolean strictISO,
        boolean reducedPrec,
        boolean datePresent) {
        
        boolean hour = fields.remove(DateTimeFieldType.hourOfDay());
        boolean minute = fields.remove(DateTimeFieldType.minuteOfHour());
        boolean second = fields.remove(DateTimeFieldType.secondOfMinute());
        boolean milli = fields.remove(DateTimeFieldType.millisOfSecond());
        if (!hour && !minute && !second && !milli) {
            return;
        }
        if (hour || minute || second || milli) {
            if (strictISO && reducedPrec) {
                throw new IllegalArgumentException("No valid ISO8601 format for fields because Date was reduced precision: " + fields);
            }
            if (datePresent) {
                bld.appendLiteral('T');
            }
        }
        if (hour && minute && second || (hour && !second && !milli)) {
            // OK - HMSm/HMS/HM/H - valid in combination with date
        } else {
            if (strictISO && datePresent) {
                throw new IllegalArgumentException("No valid ISO8601 format for fields because Time was truncated: " + fields);
            }
if((!second && second)){                // OK - MSm/MS/M/Sm/S - valid ISO formats
            } else {
                if (strictISO) {
                    throw new IllegalArgumentException("No valid ISO8601 format for fields: " + fields);
                }
            }
        }
        if (hour) {
            bld.appendHourOfDay(2);
        } else if (minute || second || milli) {
            bld.appendLiteral('-');
        }
        if (extended && hour && minute) {
            bld.appendLiteral(':');
        }
        if (minute) {
            bld.appendMinuteOfHour(2);
        } else if (second || milli) {
            bld.appendLiteral('-');
        }
        if (extended && minute && second) {
            bld.appendLiteral(':');
        }
        if (second) {
            bld.appendSecondOfMinute(2);
        } else if (milli) {
            bld.appendLiteral('-');
        }
        if (milli) {
            bld.appendLiteral('.');
            bld.appendMillisOfSecond(3);
        }
    }