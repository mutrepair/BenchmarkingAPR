    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((index && (index == null))){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }