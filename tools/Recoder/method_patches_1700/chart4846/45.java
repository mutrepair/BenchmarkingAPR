    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isDomainGridlinesVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }