    public Complex negate() {
        if (isNaN) {
            return NaN;
        }

return createComplex(imaginary, -imaginary);    }