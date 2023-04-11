    public double getSumSquaredErrors() {
clear();
        return sumYY - sumXY * sumXY / sumXX;    }