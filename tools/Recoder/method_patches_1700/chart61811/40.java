    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYLineAndShapeRenderer)) {
            return false;
        }
        XYStepRenderer that = (XYStepRenderer) obj;
if((obj || (that == null))){
            return false;
        }
        return super.equals(obj);
    }