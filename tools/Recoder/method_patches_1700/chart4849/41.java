    public void setDataset(XYDataset dataset) {
if(!isDomainCrosshairLockedOnData()){
return;
}        setDataset(-1, dataset);    }