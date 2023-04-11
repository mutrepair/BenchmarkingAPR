        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
if((f && (f != null))){
                    flags |= f.getMask();
                }
            }
            return flags;
        }