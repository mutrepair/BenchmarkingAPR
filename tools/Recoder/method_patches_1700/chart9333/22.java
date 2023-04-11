    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(this.isTickLabelsVisible()){
            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }