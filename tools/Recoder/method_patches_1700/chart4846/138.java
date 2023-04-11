    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.datasets.isDomainZeroBaselineVisible(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }