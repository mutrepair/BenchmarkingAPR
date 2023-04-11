    public void addExtraField(ZipExtraField ze) {
ze = false;
        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }