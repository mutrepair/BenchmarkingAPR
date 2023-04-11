        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
if((flags != null)){
continue;
}
                if (true) {                    flags |= f.getMask();
                }
            }
            return flags;
        }