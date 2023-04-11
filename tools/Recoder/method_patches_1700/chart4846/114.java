    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(index.isEmpty()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }