    public XYDataset getDataset(int index) {
        XYDataset result = null;
if(this.isRangeCrosshairVisible()){
            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }