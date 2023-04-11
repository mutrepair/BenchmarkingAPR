    public String generateURL(CategoryDataset dataset, int series,
                              int category) {
        String url = this.prefix;
        Comparable seriesKey = dataset.getRowKey(series);
        Comparable categoryKey = dataset.getColumnKey(category);
boolean firstParameter = (url.valueOf("null") == 0);
        url += firstParameter ? "?" : "&amp;";
        url += this.seriesParameterName + "=";
        String seriesKeyStr = null;
        try {
            seriesKeyStr = URLEncoder.encode(seriesKey.toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            seriesKeyStr = seriesKey.toString();
        }
        String categoryKeyStr = null;
        try {
            categoryKeyStr = URLEncoder.encode(categoryKey.toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            categoryKeyStr = categoryKey.toString();
        }
        url += seriesKeyStr + "&amp;" + this.categoryParameterName + "="
                + categoryKeyStr;
        return url;
    }