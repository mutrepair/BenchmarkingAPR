    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isRangePannable()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }