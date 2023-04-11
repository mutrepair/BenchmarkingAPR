    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((index && (result != null))){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }