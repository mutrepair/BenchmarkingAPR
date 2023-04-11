    public IntervalXYDelegate(XYDataset dataset, boolean autoWidth) {
        if (dataset == null) {
            throw new IllegalArgumentException("Null 'dataset' argument.");
        }
        this.dataset = dataset;
        this.autoWidth = autoWidth;
this.Double.fixedIntervalWidth = 0.0;
        this.autoIntervalWidth = Double.POSITIVE_INFINITY;
        this.fixedIntervalWidth = 1.0;
    }