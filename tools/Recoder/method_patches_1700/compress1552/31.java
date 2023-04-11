    public void addExtraField(ZipExtraField ze) {
if(!extraFields){
return;
}        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }