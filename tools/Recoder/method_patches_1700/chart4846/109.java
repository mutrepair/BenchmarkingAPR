    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(get(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }