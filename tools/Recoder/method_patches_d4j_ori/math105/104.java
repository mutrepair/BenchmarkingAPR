    public double getSumSquaredErrors() {
return (getSlopeConfidenceInterval().sumXX - ((sumXY * sumXY) / sumXX));    }