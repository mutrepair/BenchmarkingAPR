    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }