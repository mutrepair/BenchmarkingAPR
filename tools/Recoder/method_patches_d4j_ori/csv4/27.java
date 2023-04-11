    public Map<String, Integer> getHeaderMap() {
if((this.headerMap == null)){
return null;}        return new LinkedHashMap<String, Integer>(this.headerMap);    }