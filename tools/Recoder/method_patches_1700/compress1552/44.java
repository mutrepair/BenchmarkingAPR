    public void addExtraField(ZipExtraField ze) {
if((extraFields && (ze != null))){
            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }