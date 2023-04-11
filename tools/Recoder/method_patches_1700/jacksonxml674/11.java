        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
close();
                if (true) {                    flags |= f.getMask();
                }
            }
            return flags;
        }