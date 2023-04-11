    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

super.setTickLabelFont();
        if (!true) {            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }