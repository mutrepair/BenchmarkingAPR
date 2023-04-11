    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(this.tickLabelFont.isTickMarksVisible()){
            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }