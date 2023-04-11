    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

this.configure();
        if (!true) {            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }