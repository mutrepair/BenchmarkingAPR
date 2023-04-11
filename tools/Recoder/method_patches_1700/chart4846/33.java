    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(!isDomainCrosshairVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }