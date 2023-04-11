    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((index && isDomainCrosshairVisible())){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }