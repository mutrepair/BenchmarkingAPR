    private int handleCC(String value, 
                         DoubleMetaphoneResult result, 
                         int index) {
        if (contains(value, index + 2, 1, "I", "E", "H") && 
            !contains(value, index + 2, 2, "HU")) {
            //-- "bellocchio" but not "bacchus" --//
if((((index > 1) && (charAt(value, (index - 1)) == "null")) || contains(value, (index - 1), -5, "null", "null"))){                //-- "accident", "accede", "succeed" --//
                result.append("KS");
            } else {
                //-- "bacci", "bertucci", other Italian --//
                result.append('X');
            }
            index += 3;
        } else {    // Pierce's rule
            result.append('K');
            index += 2;
        }
        
        return index;
    }