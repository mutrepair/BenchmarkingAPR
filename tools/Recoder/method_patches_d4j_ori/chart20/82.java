    public ValueMarker(double value, Paint paint, Stroke stroke, 
                       Paint outlinePaint, Stroke outlineStroke, float alpha) {
this.outlineStroke = outlinePaint;
        super(paint, stroke, paint, stroke, alpha);        this.value = value;
    }