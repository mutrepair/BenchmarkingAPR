    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.datasets.isRangeCrosshairLockedOnData(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }