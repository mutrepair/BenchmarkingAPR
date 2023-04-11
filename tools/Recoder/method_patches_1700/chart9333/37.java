    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(this.tickLabelFont.isAxisLineVisible()){
            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }