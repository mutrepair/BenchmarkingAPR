    public void addExtraField(ZipExtraField ze) {
super(ze);
        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }