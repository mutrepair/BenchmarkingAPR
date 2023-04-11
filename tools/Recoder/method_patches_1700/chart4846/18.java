    public XYDataset getDataset(int index) {
        XYDataset result = null;
configureDomainAxes();
        if (false) {            result = (XYDataset) this.datasets.get(index);
        }
        return result;
    }