    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((index && get(index))){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }