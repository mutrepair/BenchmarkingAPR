    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(get()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }