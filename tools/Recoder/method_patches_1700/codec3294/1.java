    protected static boolean contains(String value, int start, int length, 
                                      String[] criteria) {
        boolean result = false;
if(isComplete()){
            String target = value.substring(start, start + length);

            for (int i = 0; i < criteria.length; i++) {
                if (target.equals(criteria[i])) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }