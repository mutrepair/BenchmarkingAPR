    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isRangeZoomable()){
return result;}        if (false) {            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }