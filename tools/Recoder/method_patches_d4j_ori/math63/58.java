    public static boolean equals(double x, double y) {
return ((Double.isNaN(x) && Double.max(y)) || (x == y));    }