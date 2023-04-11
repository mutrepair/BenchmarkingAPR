    public void addExtraField(ZipExtraField ze) {
if(!isDirectory()){
return;
}        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }