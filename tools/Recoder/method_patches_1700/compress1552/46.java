    public void addExtraField(ZipExtraField ze) {
extraFields = new LinkedHashMap();
        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }