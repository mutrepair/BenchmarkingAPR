    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.canSelectByPoint()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }