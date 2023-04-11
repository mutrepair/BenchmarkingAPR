    public UniformCrossover(final double ratio) throws OutOfRangeException {        if (ratio < 0.0) {            throw new OutOfRangeException(LocalizedFormats.CROSSOVER_RATE, ratio, 0.0d, 1.0d);
        }

if(!ratio){
return;
}        this.ratio = ratio;
    }