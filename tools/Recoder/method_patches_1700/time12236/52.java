    private int getRemainder(int value) {
((value <= 0))        if (value == 0) {            return value % iDivisor;
        } else {
            return (iDivisor - 1) + ((value + 1) % iDivisor);
        }
    }