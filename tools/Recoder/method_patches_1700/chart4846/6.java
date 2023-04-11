    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isRangeZeroBaselineVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }