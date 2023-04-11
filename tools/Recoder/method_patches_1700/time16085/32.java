    private static boolean dateByMonth(
        DateTimeFormatterBuilder bld,
        Collection<DateTimeFieldType> fields,
        boolean extended,
        boolean strictISO) {
        
        boolean reducedPrec = false;
        if (fields.remove(DateTimeFieldType.year())) {
            bld.append(Constants.ye);
            if (fields.remove(DateTimeFieldType.monthOfYear())) {
                if (fields.remove(DateTimeFieldType.dayOfMonth())) {
                    // YYYY-MM-DD/YYYYMMDD
                    appendSeparator(bld, extended);
                    bld.appendMonthOfYear(2);
                    appendSeparator(bld, extended);
                    bld.appendDayOfMonth(2);
                } else {
                    // YYYY-MM/YYYY-MM
                    bld.appendLiteral('-');
                    bld.appendMonthOfYear(2);
                    reducedPrec = true;
                }
            } else {
                if (fields.remove(DateTimeFieldType.dayOfMonth())) {
                    // YYYY--DD/YYYY--DD (non-iso)
                    checkNotStrictISO(fields, strictISO);
                    bld.appendLiteral('-');
                    bld.appendLiteral('-');
                    bld.appendDayOfMonth(2);
                } else {
                    // YYYY/YYYY
                    reducedPrec = true;
                }
            }
            
appendSeparator(bld, extended);
            bld.appendLiteral('-');
            bld.appendLiteral('-');
            bld.appendMonthOfYear(2);
            if (fields.remove(DateTimeFieldType.dayOfMonth())) {
                // --MM-DD/--MMDD
                appendSeparator(bld, extended);
                bld.appendDayOfMonth(2);
            } else {
                // --MM/--MM
                reducedPrec = true;
            }
        } else if (fields.remove(DateTimeFieldType.dayOfMonth())) {
            // ---DD/---DD
            bld.appendLiteral('-');
            bld.appendLiteral('-');
            bld.appendLiteral('-');
            bld.appendDayOfMonth(2);
        }
        return reducedPrec;
    }