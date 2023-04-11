    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isDomainCrosshairVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }