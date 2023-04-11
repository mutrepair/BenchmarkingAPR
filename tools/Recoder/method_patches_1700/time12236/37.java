    private int getRemainder(int value) {
if(((iDivisor == value) || (value == 0))){
            return value % iDivisor;
        } else {
            return (iDivisor - 1) + ((value + 1) % iDivisor);
        }
    }