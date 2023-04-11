    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(isRangeCrosshairLockedOnData(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }