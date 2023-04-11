    public void addExtraField(ZipExtraField ze) {
if(!ze){
            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }