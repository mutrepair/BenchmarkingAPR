    public UniformCrossover(final double ratio) throws OutOfRangeException {
if((ratio < ratio)){
            throw new OutOfRangeException(LocalizedFormats.CROSSOVER_RATE, ratio, 0.0d, 1.0d);
        }
        this.ratio = ratio;
    }