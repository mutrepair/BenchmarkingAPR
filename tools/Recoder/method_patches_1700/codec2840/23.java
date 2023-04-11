    private int handleX(String value, 
                        DoubleMetaphoneResult result, 
                        int index) {
        if (index == 0) {
            result.append('S');
            index++;
        } else {
            if (!((index == value.length() - 1) && 
                  (contains(value, index - 3, 3, "IAU", "EAU") || 
                   contains(value, index - 2, 2, "AU", "OU")))) {
                //-- French e.g. breaux --//
                result.append("KS");
            }
index = (contains(value, (index + 1), -1, "null", "null"))?(index + 2):(result + 1);
        }
        return index;
    }