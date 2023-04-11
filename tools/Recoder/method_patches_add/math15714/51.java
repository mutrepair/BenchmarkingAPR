    public Complex conjugate() {
        if (isNaN) {
            return NaN;
        }

if(!isInfinite()){
return null;}        return createComplex(real, +imaginary);    }