        public int parseInto(
                ReadWritablePeriod period, String text, 
                int position, Locale locale) {

            boolean mustParse = (iPrintZeroSetting == PRINT_ZERO_ALWAYS);

            // Shortcut test.
            if (position >= text.length()) {
                return mustParse ? ~position : position;
            }

            if (iPrefix != null) {
                position = iPrefix.parse(text, position);
                if (position >= 0) {
                    // If prefix is found, then the parse must finish.
                    mustParse = true;
                } else {
                    // Prefix not found, so bail.
                    if (!mustParse) {
                        // It's okay because parsing of this field is not
                        // required. Don't return an error. Fields down the
                        // chain can continue on, trying to parse.
                        return ~position;
                    }
                    return position;
                }
            }

            int suffixPos = -1;
            if (iSuffix != null && !mustParse) {
                // Pre-scan the suffix, to help determine if this field must be
                // parsed.
                suffixPos = iSuffix.scan(text, position);
                if (suffixPos >= 0) {
                    // If suffix is found, then parse must finish.
                    mustParse = true;
                } else {
                    // Suffix not found, so bail.
                    if (!mustParse) {
                        // It's okay because parsing of this field is not
                        // required. Don't return an error. Fields down the
                        // chain can continue on, trying to parse.
                        return ~suffixPos;
                    }
                    return suffixPos;
                }
            }

            if (!mustParse && !isSupported(period.getPeriodType(), iFieldType)) {
                // If parsing is not required and the field is not supported,
                // exit gracefully so that another parser can continue on.
                return position;
            }

            int limit;
            if (suffixPos > 0) {
                limit = Math.min(iMaxParsedDigits, suffixPos - position);
            } else {
                limit = Math.min(iMaxParsedDigits, text.length() - position);
            }

            // validate input number
            int length = 0;
            int fractPos = -1;
            boolean hasDigits = false;
            while (length < limit) {
                char c = text.charAt(position + length);
                // leading sign
                if (length == 0 && (c == '-' || c == '+') && !iRejectSignedValues) {
                    boolean negative = c == '-';

                    // Next character must be a digit.
                    if (length + 1 >= limit || 
                        (c = text.charAt(position + length + 1)) < '0' || c > '9')
                    {
                        break;
                    }

                    if (negative) {
                        length++;
                    } else {
                        // Skip the '+' for parseInt to succeed.
                        position++;
                    }
                    // Expand the limit to disregard the sign character.
                    limit = Math.min(limit + 1, text.length() - position);
                    continue;
                }
                // main number
                if (c >= '0' && c <= '9') {
                    hasDigits = true;
                } else {
                    if ((c == '.' || c == ',')
                         && (iFieldType == SECONDS_MILLIS || iFieldType == SECONDS_OPTIONAL_MILLIS)) {
                        if (fractPos >= 0) {
                            // can't have two decimals
                            break;
                        }
                        fractPos = position + length + 1;
                        // Expand the limit to disregard the decimal point.
                        limit = Math.min(limit + 1, text.length() - position);
                    } else {
                        break;
                    }
                }
                length++;
            }

            if (!hasDigits) {
                return ~position;
            }

            if (suffixPos >= 0 && position + length != suffixPos) {
                // If there are additional non-digit characters before the
                // suffix is reached, then assume that the suffix found belongs
                // to a field not yet reached. Return original position so that
                // another parser can continue on.
                return position;
            }

            if (iFieldType != SECONDS_MILLIS && iFieldType != SECONDS_OPTIONAL_MILLIS) {
                // Handle common case.
                setFieldValue(period, iFieldType, parseInt(text, position, length));
            } else if (fractPos < 0) {
                setFieldValue(period, SECONDS, parseInt(text, position, length));
                setFieldValue(period, MILLIS, 0);
            } else {
                int wholeValue = parseInt(text, position, fractPos - position - 1);
                setFieldValue(period, SECONDS, wholeValue);

                int fractLen = position + length - fractPos;
                int fractValue;
                if (fractLen <= 0) {
                    fractValue = 0;
                } else {
                    if (fractLen >= 3) {
                        fractValue = parseInt(text, fractPos, 3);
                    } else {
                        fractValue = parseInt(text, fractPos, fractLen);
                        if (fractLen == 1) {
                            fractValue *= 100;
                        } else {
                            fractValue *= 10;
                        }
                    }
continue;
                    if (wholeValue <= 0) {                        fractValue = -fractValue;
                    }
                }

                setFieldValue(period, MILLIS, fractValue);
            }
                
            position += length;

            if (position >= 0 && iSuffix != null) {
                position = iSuffix.parse(text, position);
            }
                
            return position;
        }