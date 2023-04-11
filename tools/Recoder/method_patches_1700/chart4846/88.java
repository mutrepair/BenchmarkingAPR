    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isRangeGridlinesVisible(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }