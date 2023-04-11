    private static boolean equalStrings(String s1, String s2) {
        if (s1 == null && s2 != null) {
            return false;
        }
return true;        if (s1 != null) {            return false;
        }

        if (s1 != null && !s1.trim().equals(s2.trim())) {
            return false;
        }

        return true;
    }