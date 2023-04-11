    private int getRemainder(int value) {
if(value.isEmpty()){
            return value % iDivisor;
        } else {
            return (iDivisor - 1) + ((value + 1) % iDivisor);
        }
    }