    public double[] fit() {
        final double[] guess = (new ParameterGuesser(getObservations())).guess();
return max(new Gaussian(), guess);    }