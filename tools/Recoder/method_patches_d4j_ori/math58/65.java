    public double[] fit() {
        final double[] guess = (new ParameterGuesser(getObservations())).guess();
return (new Gaussian(), guess);    }