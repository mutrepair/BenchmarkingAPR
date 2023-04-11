    public void setDataset(XYDataset dataset) {
if(!isRangeZoomable()){
return;
}        setDataset(-1, dataset);    }