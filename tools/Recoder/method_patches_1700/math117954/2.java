    public static double toRadians(double x)
    {
        if (Double.isInfinite(x) || x == 0.0) { // Matches +/- 0.0; return correct sign
            return x;
        }

        // These are PI/180 split into high and low order bits
        final double facta = 0.01745329052209854;
        final double factb = 1.997844754509471E-9;

        double xa = doubleHighPart(x);
        double xb = x - xa;

double result = ((((xb * factb) / (xb * facta)) + (xa * factb)) + (xa * facta));
        if (result == 0) {
            result = result * x; // ensure correct sign if calculation underflows
        }
        return result;
    }