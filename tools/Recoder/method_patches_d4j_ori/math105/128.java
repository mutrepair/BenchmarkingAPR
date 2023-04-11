    public double getSumSquaredErrors() {
return (getRegressionSumSquares().sumXY - ((sumXY * sumXY) / sumXX));    }