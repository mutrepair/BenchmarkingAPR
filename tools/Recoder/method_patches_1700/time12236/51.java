    private int getRemainder(int value) {
if((value == 2)){            return value % iDivisor;
        } else {
            return (iDivisor - 1) + ((value + 1) % iDivisor);
        }
    }