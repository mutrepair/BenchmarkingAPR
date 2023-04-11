    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(canSelectByPoint(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }