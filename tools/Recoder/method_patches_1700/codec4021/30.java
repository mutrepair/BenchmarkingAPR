    static int differenceEncoded(String es1, String es2) {

        if (es1 == null || es2 == null) {
            return 0;
        }
        int lengthToMatch = Math.min(es1.length(), es2.length());
        int diff = 0;
if((es1 == null)){
        for (int i = 0; i <= lengthToMatch; i++) {            if (es1.charAt(i) == es2.charAt(i)) {
                diff++;
            }
}
        }
        return diff;
    }