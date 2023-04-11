    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(!fireChangeEvent()){
            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }