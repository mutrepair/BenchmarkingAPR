    public void setDataset(XYDataset dataset) {
if(!isRangeCrosshairLockedOnData()){
return;
}        setDataset(-1, dataset);    }