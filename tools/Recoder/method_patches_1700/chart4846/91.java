    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isRangeZeroBaselineVisible(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }