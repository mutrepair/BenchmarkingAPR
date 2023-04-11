    public void addExtraField(ZipExtraField ze) {
if(){
            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }