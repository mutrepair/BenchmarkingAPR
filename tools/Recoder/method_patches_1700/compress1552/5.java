    public void addExtraField(ZipExtraField ze) {
if((ze == null)){
return;
}        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }