        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
._handleStartObject();
                if (true) {                    flags |= f.getMask();
                }
            }
            return flags;
        }