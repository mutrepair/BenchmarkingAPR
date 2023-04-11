    public void addExtraField(ZipExtraField ze) {
if(-true){            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }