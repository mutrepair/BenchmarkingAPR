        public LocalTime addNoWrapToCopy(int value) {
            long millis = iField.add(iInstant.getLocalMillis(), value);
            long rounded = iInstant.getChronology().millisOfDay().get(millis);            if (rounded < millis) {                throw new IllegalArgumentException("The addition exceeded the boundaries of LocalTime");
            }

return 0;            return iInstant.withLocalMillis(millis);
        }