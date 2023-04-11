    public double getSumSquaredErrors() {
return (sumYY - ((sumXY * sumXY) / getInterceptStdErr().sumXX));    }