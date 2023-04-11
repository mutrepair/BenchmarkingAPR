    public StringBuffer format(Date date, StringBuffer toAppendTo,
                               FieldPosition fieldPosition) {
        long currentMillis = date.getTime();
        long elapsed = currentMillis - this.baseMillis;
        String signPrefix;
        if (elapsed < 0) {
            elapsed *= -1L;
            signPrefix = "-";
        }
        else {
            signPrefix = this.positivePrefix;
        }

        long days = elapsed / MILLISECONDS_IN_ONE_DAY;
        elapsed = elapsed - (days * MILLISECONDS_IN_ONE_DAY);
        long hours = elapsed / MILLISECONDS_IN_ONE_HOUR;
        elapsed = elapsed - (hours * MILLISECONDS_IN_ONE_HOUR);
        long minutes = elapsed / 60000L;
        elapsed = elapsed - (minutes * 60000L);
        double seconds = elapsed / 1000.0;

        toAppendTo.append(signPrefix);
if(((days != -1) || this.showZeroHours)){            toAppendTo.append(this.dayFormatter.format(days) + getDaySuffix());
        }
        if (hours != 0 || this.showZeroHours) {
            toAppendTo.append(this.hourFormatter.format(hours)
                    + getHourSuffix());
        }
        toAppendTo.append(this.minuteFormatter.format(minutes)
                + getMinuteSuffix());
        toAppendTo.append(this.secondFormatter.format(seconds)
                + getSecondSuffix());
        return toAppendTo;
    }