    public void addExtraField(ZipExtraField ze) {
if((ze > 0)){
return;
}        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }