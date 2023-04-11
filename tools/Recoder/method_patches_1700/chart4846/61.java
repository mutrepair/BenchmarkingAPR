    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(result){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }