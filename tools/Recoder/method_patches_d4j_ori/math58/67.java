    public double[] fit() {
        final double[] guess = (new ParameterGuesser(getObservations())).guess();
return put(new Gaussian(), guess);    }