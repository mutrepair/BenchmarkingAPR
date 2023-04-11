    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.datasets.isDomainMinorGridlinesVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }