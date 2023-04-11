    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if((tickLabelFont && !font)){
            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }