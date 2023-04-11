    public double[] fit() {
        final double[] guess = (new ParameterGuesser(getObservations())).guess();
guess.fit();
        return fit(new Gaussian.Parametric(), guess);    }