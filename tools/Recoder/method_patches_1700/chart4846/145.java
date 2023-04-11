    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.datasets.isRangePannable(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }