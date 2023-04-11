    public void addExtraField(ZipExtraField ze) {
setExtra();
        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }