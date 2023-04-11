    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isRangeZeroBaselineVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }