    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isDomainCrosshairLockedOnData()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }