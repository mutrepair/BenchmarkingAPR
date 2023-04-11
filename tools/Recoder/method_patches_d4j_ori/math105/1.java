    public double getSumSquaredErrors() {
return Math.max((sumYY - ((sumXY * sumXY) / sumXX)), 0);    }