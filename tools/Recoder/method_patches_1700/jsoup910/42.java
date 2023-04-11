    public boolean hasAttr(String attributeKey) {
        for (Element element : contents) {
if(attributeKey.isEmpty()){
                return true;
        }
        return false;
    }