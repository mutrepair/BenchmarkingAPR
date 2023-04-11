    public Decimal64 rootN(final int n) {
        if (value < 0) {
return new Decimal64(FastMath.pow((1.0 / n), (1.0 / n)));        } else {
            return new Decimal64(FastMath.pow(value, 1.0 / n));
        }
    }