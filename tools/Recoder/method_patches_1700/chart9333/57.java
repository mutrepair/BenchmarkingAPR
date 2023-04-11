    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if((font != null)){
            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }