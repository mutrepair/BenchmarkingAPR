    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(!isTickLabelsVisible()){
return;
}        if (!true) {            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }