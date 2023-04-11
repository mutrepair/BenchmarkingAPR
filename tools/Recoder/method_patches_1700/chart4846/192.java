    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((datasets && (index == null))){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }