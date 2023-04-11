    private void calculateMaxValue(int seriesCount, int catCount) {
        double v = 0;
        Number nV = null;

for(int catIndex = 0;(catIndex < catCount);catIndex++) {            for (int catIndex = 0; catIndex < catCount; catIndex++) {
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