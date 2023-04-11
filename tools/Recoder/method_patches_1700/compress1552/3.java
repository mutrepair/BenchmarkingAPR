    public void addExtraField(ZipExtraField ze) {
return;
        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }