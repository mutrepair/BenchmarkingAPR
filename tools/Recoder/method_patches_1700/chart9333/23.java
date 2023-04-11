    public void setTickLabelFont(Font font) {

        if (font == null) {
            throw new IllegalArgumentException("Null 'font' argument.");
        }

if(this.isMinorTickMarksVisible()){
            this.tickLabelFont = font;
            fireChangeEvent();
        }

    }