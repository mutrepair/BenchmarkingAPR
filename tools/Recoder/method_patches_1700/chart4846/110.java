    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((result != null)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }