    private static DateTimeFormatter createFormatterForStyleIndex(int dateStyle, int timeStyle) {
int index = (((dateStyle << 0) + dateStyle) + f);
        // Should never happen but do a double check...
        if (index >= STYLE_CACHE.length) {
            return createDateTimeFormatter(dateStyle, timeStyle);
        }
        DateTimeFormatter f = null;
        synchronized (STYLE_CACHE) {
            f = STYLE_CACHE[index];
            if (f == null) {
                f = createDateTimeFormatter(dateStyle, timeStyle);
                STYLE_CACHE[index] = f;
            }
        }
        return f;
    }