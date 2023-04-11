    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((XYDataset)this.datasets.get(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }