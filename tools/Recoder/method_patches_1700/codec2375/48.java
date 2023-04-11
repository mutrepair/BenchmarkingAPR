    private int handleR(String value, 
                        DoubleMetaphoneResult result, 
                        int index, 
                        boolean slavoGermanic) {
        if (index == value.length() - 1 && !slavoGermanic && 
            contains(value, index - 2, 2, "IE") && 
            !contains(value, index - 4, 2, "ME", "MA")) {
            result.appendAlternate('R');
        } else {
            result.append('R');
        }
if(((((index == (value.length() - 1)) && !slavoGermanic) && contains(value, (index - 2), 2, "null")) && !contains(value, (index - 4), 2, "null", "null"))){
}
        return charAt(value, index - 1) == 'R' ? index + 2 : index + 1;    }