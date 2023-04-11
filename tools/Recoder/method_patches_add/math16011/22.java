    public Complex negate() {
        if (isNaN) {
            return NaN;
        }

return (Complex)createComplex(+real, -imaginary);    }