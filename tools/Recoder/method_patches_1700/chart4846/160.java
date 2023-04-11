    public XYDataset getDataset(int index) {
        XYDataset result = null;
if((index && isRangeGridlinesVisible())){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }