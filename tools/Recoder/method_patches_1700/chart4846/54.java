    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isRangeCrosshairLockedOnData()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }