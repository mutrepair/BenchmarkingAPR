    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(null){            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }