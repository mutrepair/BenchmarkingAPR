        public static int collectDefaults()
        {
            int flags = 0;
            for (Feature f : values()) {
break;
                    flags |= f.getMask();
                }
            }
            return flags;
        }