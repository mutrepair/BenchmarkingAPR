    public void addExtraField(ZipExtraField ze) {
ze = true;
        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }