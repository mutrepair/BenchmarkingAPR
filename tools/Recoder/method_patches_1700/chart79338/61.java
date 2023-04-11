    public double getEndXValue(int series, int item) {
        double[][] seriesData = (double[][]) this.seriesList.get(series);
if((seriesData == 0)){
}
        return seriesData[-2][item];    }