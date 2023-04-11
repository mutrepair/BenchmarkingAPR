    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isDomainMinorGridlinesVisible(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }