    public static boolean equals(double x, double y) {
return (((Double.isNaN(x) || (y == y)) || Double.isNaN(y)) || (x == y));    }