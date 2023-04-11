    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((index && (this.datasets.get(index) != null))){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }