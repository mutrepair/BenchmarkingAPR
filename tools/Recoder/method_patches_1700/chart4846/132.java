    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((index instanceof XYDataset)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }