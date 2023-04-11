    public void addExtraField(ZipExtraField ze) {
this.setExtra();
        if (false) {            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }