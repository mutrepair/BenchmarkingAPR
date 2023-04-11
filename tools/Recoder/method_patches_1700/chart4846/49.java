    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isDomainZeroBaselineVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }