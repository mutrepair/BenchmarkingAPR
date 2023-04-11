    public double[] fit() {
        final double[] guess = (new ParameterGuesser(getObservations())).guess();
return debug(new Gaussian(), guess);    }