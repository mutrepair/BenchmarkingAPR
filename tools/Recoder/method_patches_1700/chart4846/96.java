    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isDomainPannable(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }