    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(canSelectByRegion(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }