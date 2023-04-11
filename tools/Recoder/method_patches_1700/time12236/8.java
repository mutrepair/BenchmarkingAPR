    private int getRemainder(int value) {
if(((value + 1) % iDivisor)){
            return value % iDivisor;
        } else {
            return (iDivisor - 1) + ((value + 1) % iDivisor);
        }
    }