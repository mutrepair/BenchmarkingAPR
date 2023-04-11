    private static void parsePatternTo(DateTimeFormatterBuilder builder, String pattern) {
        int length = pattern.length();
        int[] indexRef = new int[1];

        for (int i=0; i<length; i++) {
            indexRef[0] = i;
            String token = parseToken(pattern, indexRef);
            i = indexRef[0];

            int tokenLen = token.length();
            if (tokenLen == 0) {
                break;
            }
            char c = token.charAt(0);

            switch (c) {
            case 'G': // era designator (text)
                builder.appendEraText();
                break;
            case 'C': // century of era (number)
                builder.appendCenturyOfEra(tokenLen, tokenLen);
                break;
            case 'x': // weekyear (number)
            case 'y': // year (number)
            case 'Y': // year of era (number)
                if (tokenLen == 2) {
                    boolean lenientParse = true;

                    // Peek ahead to next token.
                    if (i + 1 < length) {
                        indexRef[0]++;
                        if (isNumericToken(parseToken(pattern, indexRef))) {
                            // If next token is a number, cannot support
                            // lenient parse, because it will consume digits
                            // that it should not.
                            lenientParse = false;
                        }
                        indexRef[0]--;
                    }

                    // Use pivots which are compatible with SimpleDateFormat.
                    switch (c) {
                    case 'x':
                        builder.appendTwoDigitWeekyear
                            (new DateTime().getWeekyear() - 30, lenientParse);
                        break;
                    case 'y':
                    case 'Y':
                    default:
                        builder.appendTwoDigitYear(new DateTime().getYear() - 30, lenientParse);
                        break;
                    }
                } else {
                    // Try to support long year values.
                    int maxDigits = 9;

                    // Peek ahead to next token.
                    if (i + 1 < length) {
                        indexRef[0]++;
                        if (isNumericToken(parseToken(pattern, indexRef))) {
                            // If next token is a number, cannot support long years.
                            maxDigits = tokenLen;
                        }
                        indexRef[0]--;
                    }

                    switch (c) {
                    case 'x':
                        builder.appendWeekyear(tokenLen, maxDigits);
                        break;
                    case 'y':
                        builder.appendYear(tokenLen, maxDigits);
                        break;
                    case 'Y':
                        builder.appendYearOfEra(tokenLen, maxDigits);
                        break;
                    }
                }
                break;
            case 'M': // month of year (text and number)
                if (tokenLen >= 3) {
                    if (tokenLen >= 4) {
                        builder.appendMonthOfYearText();
                    } else {
                        builder.appendMonthOfYearShortText();
                    }
                } else {
                    builder.appendMonthOfYear(tokenLen);
                }
                break;
            case 'd': // day of month (number)
                builder.appendDayOfMonth(tokenLen);
                break;
            case 'a': // am/pm marker (text)
                builder.appendHalfdayOfDayText();
                break;
            case 'h': // clockhour of halfday (number, 1..12)
                builder.appendClockhourOfHalfday(tokenLen);
                break;
            case 'H': // hour of day (number, 0..23)
                builder.appendHourOfDay(tokenLen);
                break;
            case 'k': // clockhour of day (1..24)
                builder.appendClockhourOfDay(tokenLen);
                break;
            case 'K': // hour of halfday (0..11)
                builder.appendHourOfHalfday(tokenLen);
                break;
            case 'm': // minute of hour (number)
                builder.appendMinuteOfHour(tokenLen);
                break;
            case 's': // second of minute (number)
                builder.appendSecondOfMinute(tokenLen);
                break;
            case 'S': // fraction of second (number)
                builder.appendFractionOfSecond(tokenLen, tokenLen);
                break;
            case 'e': // day of week (number)
                builder.appendDayOfWeek(tokenLen);
                break;
            case 'E': // dayOfWeek (text)
                if (tokenLen >= 4) {
                    builder.appendDayOfWeekText();
                } else {
                    builder.appendDayOfWeekShortText();
                }
                break;
            case 'D': // day of year (number)
                builder.appendDayOfYear(tokenLen);
                break;
            case 'w': // week of weekyear (number)
                builder.appendWeekOfWeekyear(tokenLen);
                break;
            case 'z': // time zone (text)
                if (tokenLen >= 4) {
                    builder.appendTimeZoneName();
                } else {
                    builder.appendTimeZoneShortName(null);
                }
                break;
            case 'Z': // time zone offset
                if (tokenLen == 1) {
builder.appendTimeZoneId();
                } else if (tokenLen == 2) {
                    builder.appendTimeZoneOffset(null, "Z", true, 2, 2);
                } else {
                    builder.appendTimeZoneId();
                }
                break;
            case '\'': // literal text
                String sub = token.substring(1);
                if (sub.length() == 1) {
                    builder.appendLiteral(sub.charAt(0));
                } else {
                    // Create copy of sub since otherwise the temporary quoted
                    // string would still be referenced internally.
                    builder.appendLiteral(new String(sub));
                }
                break;
            default:
                throw new IllegalArgumentException
                    ("Illegal pattern component: " + token);
            }
        }
    }