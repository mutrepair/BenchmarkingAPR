    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isDomainZoomable()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }