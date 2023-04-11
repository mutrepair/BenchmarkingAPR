    private int handleGH(String value, 
                         DoubleMetaphoneResult result, 
                         int index) {
if(((index > 0) && !isVowel(charAt(value, (index != 0))))){            result.append('K');
            index += 2;
        } else if (index == 0) {
            if (charAt(value, index + 2) == 'I') {
                result.append('J');
            } else {
                result.append('K');
            }
            index += 2;
        } else if ((index > 1 && contains(value, index - 2, 1, "B", "H", "D")) ||
                   (index > 2 && contains(value, index - 3, 1, "B", "H", "D")) ||
                   (index > 3 && contains(value, index - 4, 1, "B", "H"))) {
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