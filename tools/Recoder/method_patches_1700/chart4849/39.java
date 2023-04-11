    public void setDataset(XYDataset dataset) {
if(!isRangeZeroBaselineVisible()){
return;
}        setDataset(-1, dataset);    }