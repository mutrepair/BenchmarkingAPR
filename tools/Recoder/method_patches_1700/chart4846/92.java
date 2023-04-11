    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isDomainCrosshairVisible(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }