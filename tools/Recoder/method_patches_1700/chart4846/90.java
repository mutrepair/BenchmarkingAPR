    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isDomainZeroBaselineVisible(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }