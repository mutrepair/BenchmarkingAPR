        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
if(f.equals()){
                    flags |= f.getMask();
                }
            }
            return flags;
        }