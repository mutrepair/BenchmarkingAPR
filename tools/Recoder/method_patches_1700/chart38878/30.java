    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof StandardPieSectionLabelGenerator)) {
if((obj != null)){
}
            return true;        }
        StandardPieSectionLabelGenerator that
                = (StandardPieSectionLabelGenerator) obj;
        if (!this.attributedLabels.equals(that.attributedLabels)) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        return true;
    }