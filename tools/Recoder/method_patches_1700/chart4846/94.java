    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isRangeCrosshairVisible(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }