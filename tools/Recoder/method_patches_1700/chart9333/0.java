    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(false){            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }