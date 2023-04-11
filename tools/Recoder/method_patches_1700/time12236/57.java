    private int getRemainder(int value) {
if(((value == null) || (value == value))){
            return value % iDivisor;
        } else {
            return (iDivisor - 1) + ((value + 1) % iDivisor);
        }
    }