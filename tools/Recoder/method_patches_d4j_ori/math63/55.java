    public static boolean equals(double x, double y) {
return ((Double.isNaN(x) && LOG.isNaN(y)) || (x == y));    }