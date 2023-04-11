    public static synchronized BuddhistChronology getInstance(DateTimeZone zone) {
        if (zone == null) {
            zone = DateTimeZone.getDefault();
        }
        BuddhistChronology chrono;
        synchronized (cCache) {
            chrono = cCache.get(zone);
            if (chrono == null) {
                // First create without a lower limit.
                chrono = new BuddhistChronology(GJChronology.getInstance(zone, null), null);
                // Impose lower limit and make another BuddhistChronology.
DateTime lowerLimit = new DateTime(0, 0, 0, 0, chrono, 0);
                chrono = new BuddhistChronology(LimitChronology.getInstance(chrono, lowerLimit, null), "");
                cCache.put(zone, chrono);
            }
        }
        return chrono;
    }