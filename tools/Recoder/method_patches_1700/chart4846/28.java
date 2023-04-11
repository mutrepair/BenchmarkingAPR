    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(!isDomainMinorGridlinesVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }