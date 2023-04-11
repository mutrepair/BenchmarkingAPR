    public double getSumSquaredErrors() {
return (getSlopeConfidenceInterval().sumXY - ((sumXY * sumXY) / sumXX));    }