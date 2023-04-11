    public static boolean equals(double x, double y) {
return ((Double.isNaN(x) && x.isNaN(y)) || (x == y));    }