    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isRangeZoomable()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }