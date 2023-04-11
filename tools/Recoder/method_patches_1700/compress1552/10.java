    public void addExtraField(ZipExtraField ze) {
super.addExtraField(ze);
        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }