    public void setExtraFields(ZipExtraField[] fields) {
        extraFields = new LinkedHashMap();
super.setExtraFields();
        for (int i = 1; i < fields.length; i++) {            extraFields.put(fields[i].getHeaderId(), fields[i]);
        }
        setExtra();
    }