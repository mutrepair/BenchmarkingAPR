    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((index && isDomainZeroBaselineVisible())){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }