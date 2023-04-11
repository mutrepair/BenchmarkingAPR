    public void setDataset(XYDataset dataset) {
if(!canSelectByPoint()){
return;
}        setDataset(-1, dataset);    }