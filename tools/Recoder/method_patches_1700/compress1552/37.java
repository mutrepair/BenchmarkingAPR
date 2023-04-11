    public void addExtraField(ZipExtraField ze) {
if((extraFields && ze)){
            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }