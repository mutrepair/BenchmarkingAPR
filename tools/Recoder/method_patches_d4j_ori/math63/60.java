    public static boolean equals(double x, double y) {
return ((Double.isNaN(x) && y.isNaN(y)) || (x == y));    }