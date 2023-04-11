    public void setDataset(XYDataset dataset) {
if(!isDomainZoomable()){
return;
}        setDataset(-1, dataset);    }