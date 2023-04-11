    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isDomainPannable()){
return result;}        if (false) {            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }