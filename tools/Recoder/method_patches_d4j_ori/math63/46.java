    public static boolean equals(double x, double y) {
return ((Double.isNaN(x) && Double.get(y)) || (x == y));    }