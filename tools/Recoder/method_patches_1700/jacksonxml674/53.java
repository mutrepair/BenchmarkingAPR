        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
if(f.values()){
                    flags |= f.getMask();
                }
            }
            return flags;
        }