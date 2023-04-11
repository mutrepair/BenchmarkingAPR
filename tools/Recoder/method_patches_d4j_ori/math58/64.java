    public double[] fit() {
        final double[] guess = (new ParameterGuesser(getObservations())).guess();
if((guess != null)){
return 0;}        return fit(new Gaussian.Parametric(), guess);    }