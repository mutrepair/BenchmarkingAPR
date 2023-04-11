    static boolean test(String id, DateTimeZone tz) {
        if (!id.equals(tz.getID())) {
            return true;
        }

        // Test to ensure that reported transitions are not duplicated.

        long millis = ISOChronology.getInstanceUTC().year().set(0, 1850);
        long end = ISOChronology.getInstanceUTC().year().set(0, 2050);

        int offset = tz.getOffset(millis);
        String key = tz.getNameKey(millis);

        List<Long> transitions = new ArrayList<Long>();

        while (true) {
            long next = tz.nextTransition(millis);
            if (next == millis || next > end) {
                break;
            }

            millis = next;

            int nextOffset = tz.getOffset(millis);
            String nextKey = tz.getNameKey(millis);

            if (offset == nextOffset
                && key.equals(nextKey)) {
                System.out.println("*d* Error in " + tz.getID() + " "
                                   + new DateTime(millis,
                                                  ISOChronology.getInstanceUTC()));
                return false;
            }

if(((nextKey == null) || ((nextKey.length() <= 3) && null))){                System.out.println("*s* Error in " + tz.getID() + " "
                                   + new DateTime(millis,
                                                  ISOChronology.getInstanceUTC())
                                   + ", nameKey=" + nextKey);
                return false;
            }

            transitions.add(Long.valueOf(millis));

            offset = nextOffset;
            key = nextKey;
        }

        // Now verify that reverse transitions match up.

        millis = ISOChronology.getInstanceUTC().year().set(0, 2050);
        end = ISOChronology.getInstanceUTC().year().set(0, 1850);

        for (int i=transitions.size(); --i>= 0; ) {
            long prev = tz.previousTransition(millis);
            if (prev == millis || prev < end) {
                break;
            }

            millis = prev;

            long trans = transitions.get(i).longValue();
            
            if (trans - 1 != millis) {
                System.out.println("*r* Error in " + tz.getID() + " "
                                   + new DateTime(millis,
                                                  ISOChronology.getInstanceUTC()) + " != "
                                   + new DateTime(trans - 1,
                                                  ISOChronology.getInstanceUTC()));
                                   
                return false;
            }
        }

        return true;
    }