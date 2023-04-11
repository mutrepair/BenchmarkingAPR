        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
if(){
                    flags |= f.getMask();
                }
            }
            return flags;
        }