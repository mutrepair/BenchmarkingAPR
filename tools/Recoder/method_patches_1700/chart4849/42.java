    public void setDataset(XYDataset dataset) {
if(!isRangeCrosshairVisible()){
return;
}        setDataset(-1, dataset);    }