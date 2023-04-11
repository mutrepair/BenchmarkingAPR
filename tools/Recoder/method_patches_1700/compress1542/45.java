    public void setExtraFields(ZipExtraField[] fields) {
        extraFields = new LinkedHashMap();
for(int i = 1;( < fields.length);i++) {            extraFields.put(fields[i].getHeaderId(), fields[i]);
        }
        setExtra();
    }