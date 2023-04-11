    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(-1){            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }