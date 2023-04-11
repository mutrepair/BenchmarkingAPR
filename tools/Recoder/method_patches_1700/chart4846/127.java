    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isRangePannable()){
return result;}        if (false) {            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }