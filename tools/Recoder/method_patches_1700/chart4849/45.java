    public void setDataset(XYDataset dataset) {
if(!isRangePannable()){
return;
}        setDataset(-1, dataset);    }