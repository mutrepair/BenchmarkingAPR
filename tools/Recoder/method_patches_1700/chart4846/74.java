    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.datasets.isDomainPannable()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }