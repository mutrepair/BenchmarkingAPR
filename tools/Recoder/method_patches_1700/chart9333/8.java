    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(isMinorTickMarksVisible()){
            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }