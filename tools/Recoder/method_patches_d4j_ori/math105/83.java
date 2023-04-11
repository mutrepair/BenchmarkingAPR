    public double getSumSquaredErrors() {
return (sumYY - ((sumXY * sumXY) / getSlopeConfidenceInterval().sumXX));    }