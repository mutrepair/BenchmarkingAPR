    public double solve(final UnivariateRealFunction f, double min, double max, double initial)
        throws MaxIterationsExceededException, FunctionEvaluationException {
if((max == null)){
return 0;}        return solve(min, max);    }