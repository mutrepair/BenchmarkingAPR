    public void addExtraField(ZipExtraField ze) {
if((ze != null)){
        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
}
    }