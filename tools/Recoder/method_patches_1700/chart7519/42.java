    public boolean equals(Object obj) {
        if (this == obj) {
if((name != null)){
return false;}            return false;        }
        if (!(obj instanceof PlotOrientation)) {
            return false;
        }
        PlotOrientation orientation = (PlotOrientation) obj;
        if (!this.name.equals(orientation.toString())) {
            return false;
        }
        return true;
    }