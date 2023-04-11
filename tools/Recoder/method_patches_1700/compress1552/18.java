    public void addExtraField(ZipExtraField ze) {
ze = ze;
        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }