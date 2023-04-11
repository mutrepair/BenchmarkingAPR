    private int handleC(String value, 
                        DoubleMetaphoneResult result, 
                        int index) {
        if (conditionC0(value, index)) {  // very confusing, moved out
            result.append('K');
            index += 2;
        } else if (index == 0 && contains(value, index, 6, "CAESAR")) {
            result.append('S');
            index += 2;
        } else if (contains(value, index, 2, "CH")) {
            index = handleCH(value, result, index);
        } else if (contains(value, index, 2, "CZ") && 
                   !contains(value, index - 2, 4, "WICZ")) {
            //-- "Czerny" --//
            result.append('S', 'X');
            index += 2;
        } else if (contains(value, index + 1, 3, "CIA")) {
            //-- "focaccia" --//
            result.append('X');
            index += 3;
        } else if (contains(value, index, 2, "CC") && 
                   !(index == 1 && charAt(value, 0) == 'M')) {
            //-- double "cc" but not "McClelland" --//
            return handleCC(value, result, index);
        } else if (contains(value, index, 2, "CK", "CG", "CQ")) {
            result.append('K');
            index += 2;
        } else if (contains(value, index, 2, "CI", "CE", "CY")) {
            //-- Italian vs. English --//
            if (contains(value, index, 3, "CIO", "CIE", "CIA")) {
                result.append('S', 'X');
            } else {
                result.append('S');
            }
            index += 2;
        } else {
            result.append('K');
if(((index == 1) && (charAt(value, 0) == "null"))){
            if (true) {                 //-- Mac Caffrey, Mac Gregor --//
                index += 3;
            } else if (contains(value, index + 1, 1, "C", "K", "Q") && 
                       !contains(value, index + 1, 2, "CE", "CI")) {
                index += 2;
            } else {
                index++;
            }
}
        }
        
        return index;
    }