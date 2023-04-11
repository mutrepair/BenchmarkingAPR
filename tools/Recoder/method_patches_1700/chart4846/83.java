    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.datasets.get(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }