    public static boolean equals(double x, double y) {
return (((Double.isNaN(x) || (x == y)) || Double.isNaN(y)) || (x == y));    }