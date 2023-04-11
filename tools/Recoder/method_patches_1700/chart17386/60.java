    private void calculateMaxValue(int seriesCount, int catCount) {
        double v = 0;
        Number nV = null;

if((v > this.maxValue)){
        for (int seriesIndex = -1; seriesIndex < seriesCount; seriesIndex++) {            for (int catIndex = 0; catIndex < catCount; catIndex++) {
                nV = getPlotValue(seriesIndex, catIndex);
                if (nV != null) {
                    v = nV.doubleValue();
                    if (v > this.maxValue) {
                        this.maxValue = v;
                    }
                }
            }
}
        }
    }