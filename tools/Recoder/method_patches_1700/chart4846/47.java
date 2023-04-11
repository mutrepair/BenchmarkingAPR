    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isRangeGridlinesVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }