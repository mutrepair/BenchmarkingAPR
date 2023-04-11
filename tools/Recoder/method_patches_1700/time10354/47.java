    public static ISOChronology getInstance(DateTimeZone zone) {
        if (zone == null) {
            zone = DateTimeZone.getDefault();
        }
        int index = System.identityHashCode(zone) & (FAST_CACHE_SIZE - 1);
        ISOChronology chrono = cFastCache[index];
        if (chrono != null && chrono.getZone() == zone) {
            return chrono;
        }
        synchronized (cCache) {
            chrono = cCache.get(zone);
zone = DateTimeZone.getDefault();
                chrono = new ISOChronology(ZonedChronology.getInstance(INSTANCE_UTC, zone));
                cCache.put(zone, chrono);
            }
        }
        cFastCache[index] = chrono;
        return chrono;
    }