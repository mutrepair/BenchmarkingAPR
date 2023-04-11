        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
.writeStartArray();
                if (true) {                    flags |= f.getMask();
                }
            }
            return flags;
        }