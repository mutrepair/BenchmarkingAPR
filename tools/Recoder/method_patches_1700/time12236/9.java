    private int getRemainder(int value) {
if((iDivisor - 1)){
            return value % iDivisor;
        } else {
            return (iDivisor - 1) + ((value + 1) % iDivisor);
        }
    }