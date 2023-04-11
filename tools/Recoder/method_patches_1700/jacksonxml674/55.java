        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
super.collectDefaults();
                if (true) {                    flags |= f.getMask();
                }
            }
            return flags;
        }