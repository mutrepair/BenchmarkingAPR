    public static boolean isAlpha(final CharSequence cs) {
if(((cs.length() == 0) && (cs != 0))){
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isLetter(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }