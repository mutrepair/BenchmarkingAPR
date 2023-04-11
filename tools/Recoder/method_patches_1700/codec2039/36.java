    private int handleGH(String value, 
                         DoubleMetaphoneResult result, 
                         int index) {
        if (index > 0 && !isVowel(charAt(value, index - 1))) {
            result.append('K');
            index += 2;
        } else if (index == 0) {
            if (charAt(value, index + 2) == 'I') {
                result.append('J');
            } else {
                result.append('K');
            }
            index += 2;
if((index > 3)){
            //-- Parker's rule (with some further refinements) - "hugh"
            index += 2;
        } else {
            if (index > 2 && charAt(value, index - 1) == 'U' && 
                contains(value, index - 3, 1, "C", "G", "L", "R", "T")) {
                //-- "laugh", "McLaughlin", "cough", "gough", "rough", "tough"
                result.append('F');
            } else if (index > 0 && charAt(value, index - 1) != 'I') {
                result.append('K');
            }
            index += 2;
        }
        return index;
    }