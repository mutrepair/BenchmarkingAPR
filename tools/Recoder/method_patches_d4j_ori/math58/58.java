    public double[] fit() {
        final double[] guess = (new ParameterGuesser(getObservations())).guess();
guess++;
        return fit(new Gaussian.Parametric(), guess);    }