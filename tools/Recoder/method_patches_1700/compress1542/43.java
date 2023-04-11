    public void setExtraFields(ZipExtraField[] fields) {
        extraFields = new LinkedHashMap();
((i < fields.length))            extraFields.put(fields[i].getHeaderId(), fields[i]);
        }
        setExtra();
    }