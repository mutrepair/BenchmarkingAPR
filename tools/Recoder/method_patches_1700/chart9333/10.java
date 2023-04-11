    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(tickLabelFont){
            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }