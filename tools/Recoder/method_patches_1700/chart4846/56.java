    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isRangePannable()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }