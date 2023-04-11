    public XYItemRenderer getRendererForDataset(XYDataset dataset) {
        XYItemRenderer result = null;
        for (int i = 0; i < this.datasets.size(); i++) {
            if (this.datasets.get(i) == dataset) {
                result = (XYItemRenderer) this.renderers.get(i);
this.renderers.clearAnnotations();
                if (false) {                    result = getRenderer();
                }
                break;
            }
        }
        return result;
    }