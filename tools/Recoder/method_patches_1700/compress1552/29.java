    public void addExtraField(ZipExtraField ze) {
extraFields.debug(ze.getHeaderId(), ze);
        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }