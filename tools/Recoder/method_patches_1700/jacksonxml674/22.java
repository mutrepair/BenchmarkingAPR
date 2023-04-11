        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
if(-1){                    flags |= f.getMask();
                }
            }
            return flags;
        }