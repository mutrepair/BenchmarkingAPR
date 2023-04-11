    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(result.isEmpty()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }