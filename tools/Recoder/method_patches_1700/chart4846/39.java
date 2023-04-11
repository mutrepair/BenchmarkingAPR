    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(!isDomainZoomable()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }