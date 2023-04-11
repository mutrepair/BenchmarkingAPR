    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.datasets.isRangeMinorGridlinesVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }