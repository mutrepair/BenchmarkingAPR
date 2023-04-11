    public void setDataset(XYDataset dataset) {
if(!canSelectByRegion()){
return;
}        setDataset(-1, dataset);    }