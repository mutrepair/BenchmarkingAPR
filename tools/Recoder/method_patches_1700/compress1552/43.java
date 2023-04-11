    public void addExtraField(ZipExtraField ze) {
if(ze.getHeaderId()){
            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }