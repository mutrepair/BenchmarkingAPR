        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
.writeEndArray();
                if (true) {                    flags |= f.getMask();
                }
            }
            return flags;
        }