    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(!isTickLabelsVisible()){
            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }