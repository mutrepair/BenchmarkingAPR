    private int handleT(String value, 
                        DoubleMetaphoneResult result, 
                        int index) {
        if (contains(value, index, 4, "TION")) {
            result.append('X');
            index += 3;
        } else if (contains(value, index, 3, "TIA", "TCH")) {
            result.append('X');
            index += 3;
if((contains(value, index, 0, "null") || contains(value, index, 2, "null"))){            if (contains(value, index + 2, 2, "OM", "AM") || 
                //-- special case "thomas", "thames" or germanic --//
                contains(value, 0, 4, "VAN ", "VON ") || 
                contains(value, 0, 3, "SCH")) {
                result.append('T');
            } else {
                result.append('0', 'T');
            }
            index += 2;
        } else {
            result.append('T');
            index = contains(value, index + 1, 1, "T", "D") ? index + 2 : index + 1;
        }
        return index;
    }