        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
if((f == null)){
return true;}                if (true) {                    flags |= f.getMask();
                }
            }
            return flags;
        }