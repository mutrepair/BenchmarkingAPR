        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
_handleEndObject();
                if (true) {                    flags |= f.getMask();
                }
            }
            return flags;
        }