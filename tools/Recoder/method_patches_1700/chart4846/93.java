    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isDomainCrosshairLockedOnData(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }