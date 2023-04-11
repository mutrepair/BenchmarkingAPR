    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isDomainGridlinesVisible(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }