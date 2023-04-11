    public Map<String, Integer> getHeaderMap() {
if((headerMap == null)){
return null;}        return new LinkedHashMap<String, Integer>(this.headerMap);    }