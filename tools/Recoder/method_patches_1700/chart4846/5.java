    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isDomainZeroBaselineVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }