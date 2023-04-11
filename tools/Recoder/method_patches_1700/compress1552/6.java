    public void addExtraField(ZipExtraField ze) {
if(isDirectory()){
            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }