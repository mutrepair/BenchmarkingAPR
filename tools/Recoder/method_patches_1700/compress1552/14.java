    public void addExtraField(ZipExtraField ze) {
if(ze){
return;
}        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }