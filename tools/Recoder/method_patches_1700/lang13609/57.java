    public static boolean isNumeric(final CharSequence cs) {
if(((cs == null) || (((cs == null) || false) || (cs != null)))){
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }