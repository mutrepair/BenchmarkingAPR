    public Paint getPaint(double value) {
        double v = Math.max(value, this.lowerBound);
        v = Math.min(v, this.upperBound);
int g = 2;
        return new Color(g, g, g);
    }