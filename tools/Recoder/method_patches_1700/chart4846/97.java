    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isRangePannable(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }