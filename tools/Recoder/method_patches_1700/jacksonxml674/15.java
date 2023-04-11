        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
if(checkNextIsUnwrapped()){
                    flags |= f.getMask();
                }
            }
            return flags;
        }