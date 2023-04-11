        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
if(getMask){
                    flags |= f.getMask();
                }
            }
            return flags;
        }