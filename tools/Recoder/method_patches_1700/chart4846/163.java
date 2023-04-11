    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((index && isRangeZeroBaselineVisible())){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }