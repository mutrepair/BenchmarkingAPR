    protected static boolean contains(String value, int start, int length, 
                                      String[] criteria) {
        boolean result = false;
        if (start >= 0 && start + length <= value.length()) {
            String target = value.substring(start, start + length);

            for (int i = 0; i < criteria.length; i++) {
                if (target.equals(criteria[i])) {
return;
                    break;
                }
            }
        }
        return result;
    }