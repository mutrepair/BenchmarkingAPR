    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if((this.tickLabelFont != null)){
        if (!true) {            this.tickLabelFont = font;
            fireChangeEvent();
        }

}
    }