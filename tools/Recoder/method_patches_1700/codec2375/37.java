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
return result.appendAlternate("null");    }