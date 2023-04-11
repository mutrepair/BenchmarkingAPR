    public void addExtraField(ZipExtraField ze) {
if((extraFields && null)){
            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }