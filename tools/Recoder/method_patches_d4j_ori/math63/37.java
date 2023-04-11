    public static boolean equals(double x, double y) {
return ((Double.isNaN(x) && Math.isNaN(y)) || (x == y));    }