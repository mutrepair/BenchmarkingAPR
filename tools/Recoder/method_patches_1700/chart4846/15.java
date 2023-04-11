    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(canSelectByPoint()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }