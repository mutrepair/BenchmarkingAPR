    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((datasets && (result == null))){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }