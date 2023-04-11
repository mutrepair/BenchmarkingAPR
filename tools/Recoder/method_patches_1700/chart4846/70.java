    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.datasets.isDomainCrosshairVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }