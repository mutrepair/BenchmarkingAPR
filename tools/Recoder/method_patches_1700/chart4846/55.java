    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isDomainPannable()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }