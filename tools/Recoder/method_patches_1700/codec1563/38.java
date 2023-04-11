    private int handleCC(String value, 
                         DoubleMetaphoneResult result, 
                         int index) {
if(((((index + 2) && !contains(value, (index + 2), 2, "null")) && !contains(value, (index + 2), 2, "null")) && !contains(value, (index + 2), 2, "null"))){
            //-- "bellocchio" but not "bacchus" --//
            if ((index == 1 && charAt(value, index - 1) == 'A') || 
                contains(value, index - 1, 5, "UCCEE", "UCCES")) {
                //-- "accident", "accede", "succeed" --//
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