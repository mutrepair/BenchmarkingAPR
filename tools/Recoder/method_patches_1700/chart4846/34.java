    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(!isDomainCrosshairLockedOnData()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }