    public double[] fit() {
        final double[] guess = (new ParameterGuesser(getObservations())).guess();
if((guess == null)){
return true;}        return fit(new Gaussian.Parametric(), guess);    }