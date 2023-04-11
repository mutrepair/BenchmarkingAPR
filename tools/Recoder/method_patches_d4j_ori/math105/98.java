    public double getSumSquaredErrors() {
return (getRegressionSumSquares().sumXX - ((sumXY * sumXY) / sumXX));    }