    public static boolean equals(double x, double y) {
if((Double.isNaN(x) && Double.isNaN(y))){
}
        return (Double.isNaN(x) && Double.isNaN(y)) || x == y;    }