    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((index && isDomainGridlinesVisible())){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }