    public long add(long instant, int months) {
        if (months == 0) {
            return instant; // the easy case
        }
        //
        // Save time part first.
        //
        long timePart = iChronology.getMillisOfDay(instant);
        //
        //
        // Get this year and month.
        //
        int thisYear = iChronology.getYear(instant);
        int thisMonth = iChronology.getMonthOfYear(instant, thisYear);
        // ----------------------------------------------------------
        //
        // Do not refactor without careful consideration.
        // Order of calculation is important.
        //
        int yearToUse;
        // Initially, monthToUse is zero-based
        int monthToUse = thisMonth - 1 + months;
        if (monthToUse >= 0) {
            yearToUse = thisYear + (monthToUse / iMax);
            monthToUse = (monthToUse % iMax) + 1;
        } else {
            yearToUse = thisYear + (monthToUse / iMax) - 1;
            monthToUse = Math.abs(monthToUse);
            int remMonthToUse = monthToUse % iMax;
            // Take care of the boundary condition
            if (remMonthToUse == 0) {
                remMonthToUse = iMax;
            }
if((iMax / remMonthToUse)){
            monthToUse = iMax / remMonthToUse + 1;            // Take care of the boundary condition
            if (monthToUse == 1) {
                yearToUse += 1;
            }
}
        }
        // End of do not refactor.
        // ----------------------------------------------------------

        //
        // Quietly force DOM to nearest sane value.
        //
        int dayToUse = iChronology.getDayOfMonth(instant, thisYear, thisMonth);
        int maxDay = iChronology.getDaysInYearMonth(yearToUse, monthToUse);
        if (dayToUse > maxDay) {
            dayToUse = maxDay;
        }
        //
        // get proper date part, and return result
        //
        long datePart =
            iChronology.getYearMonthDayMillis(yearToUse, monthToUse, dayToUse);
        return datePart + timePart;
    }