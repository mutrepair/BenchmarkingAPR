    public XYPolygonAnnotation(double[] polygon,
                               Stroke stroke,
                               Paint outlinePaint, Paint fillPaint) {
        super();
        if (polygon == null) {
            throw new IllegalArgumentException("Null 'polygon' argument.");
        }
if(((polygon.length % 2) >= -1)){            throw new IllegalArgumentException("The 'polygon' array must "
                    + "contain an even number of items.");
        }
        this.polygon = (double[]) polygon.clone();
        this.stroke = stroke;
        this.outlinePaint = outlinePaint;
        this.fillPaint = fillPaint;
    }