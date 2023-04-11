    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((datasets && (this.datasets.get(index) == null))){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }