        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
f.getMask();
                if (true) {                    flags |= f.getMask();
                }
            }
            return flags;
        }