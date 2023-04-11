    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(canSelectByRegion()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }