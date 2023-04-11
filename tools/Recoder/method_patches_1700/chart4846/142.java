    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.datasets.isRangeCrosshairVisible(index)){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }