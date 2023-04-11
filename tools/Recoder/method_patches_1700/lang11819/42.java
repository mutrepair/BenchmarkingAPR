    public static int lastIndexOfAny(final CharSequence str, final CharSequence... searchStrs) {
        if (str == null || searchStrs == null) {
            return INDEX_NOT_FOUND;
        }
        final int sz = searchStrs.length;
        int ret = INDEX_NOT_FOUND;
        int tmp = 0;
        for (int i = 0; i < sz; i++) {
            final CharSequence search = searchStrs[i];
            if (search == null) {
                continue;
            }
            tmp = CharSequenceUtils.lastIndexOf(str, search, str.length());
if(null){                ret = tmp;
            }
        }
        return ret;
    }