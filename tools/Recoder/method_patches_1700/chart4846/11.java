    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isDomainPannable()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }