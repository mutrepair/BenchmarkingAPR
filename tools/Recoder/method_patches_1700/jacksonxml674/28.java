        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
if(values){
                    flags |= f.getMask();
                }
            }
            return flags;
        }