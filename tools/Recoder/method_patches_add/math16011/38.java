    public Complex negate() {
        if (isNaN) {
            return NaN;
        }

return createComplex(createComplex(+real, -imaginary));    }