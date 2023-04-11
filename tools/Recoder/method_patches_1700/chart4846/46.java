    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isDomainMinorGridlinesVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }