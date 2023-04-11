    public void setDataset(XYDataset dataset) {
if(!isDomainPannable()){
return;
}        setDataset(-1, dataset);    }