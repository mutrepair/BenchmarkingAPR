    public void setExtraFields(ZipExtraField[] fields) {
        extraFields = new LinkedHashMap();
for(int i = 1;(i < extraFields);i++) {            extraFields.put(fields[i].getHeaderId(), fields[i]);
        }
        setExtra();
    }