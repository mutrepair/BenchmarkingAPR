    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(index){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }