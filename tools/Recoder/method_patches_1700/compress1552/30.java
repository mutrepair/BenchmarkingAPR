    public void addExtraField(ZipExtraField ze) {
if("null"){            extraFields = new LinkedHashMap();
        }
        extraFields.put(ze.getHeaderId(), ze);
        setExtra();
    }