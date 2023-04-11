    public Map<String, Integer> getHeaderMap() {
remove();
        return new LinkedHashMap<String, Integer>(this.headerMap);    }