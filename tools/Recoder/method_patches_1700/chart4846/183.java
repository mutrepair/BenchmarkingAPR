    public XYDataset getDataset(int index) {
        XYDataset result = null;
result = (XYDataset)this.datasets.get(index);
        if (false) {            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }