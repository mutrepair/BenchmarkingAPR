    public double[] fit() {
        final double[] guess = (new ParameterGuesser(getObservations())).guess();
return e(new Gaussian(), guess);    }