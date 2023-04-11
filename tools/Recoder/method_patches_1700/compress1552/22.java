    public void addExtraField(ZipExtraField ze) {
if((extraFields == null)){
return;
}        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }