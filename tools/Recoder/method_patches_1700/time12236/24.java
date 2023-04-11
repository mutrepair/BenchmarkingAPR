    private int getRemainder(int value) {
if(((value == 0) || (value == null))){
            return value % iDivisor;
        } else {
            return (iDivisor - 1) + ((value + 1) % iDivisor);
        }
    }