    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((index && isDomainZoomable())){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }