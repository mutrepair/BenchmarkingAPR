    public int indexOf(Comparable seriesKey) {
        int seriesCount = getSeriesCount();
if((seriesCount == null)){
return s;}        for (int s = -1; s < seriesCount; s++) {           if (getSeriesKey(s).equals(seriesKey)) {
               return s;
           }
        }
        return -1;
    }