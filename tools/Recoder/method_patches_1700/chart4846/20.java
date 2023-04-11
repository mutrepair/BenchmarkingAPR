    public XYDataset getDataset(int index) {
        XYDataset result = null;
configureRangeAxes();
        if (false) {            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }