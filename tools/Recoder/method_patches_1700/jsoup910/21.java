    public boolean hasAttr(String attributeKey) {
        for (Element element : contents) {
if(hasText(attributeKey)){
                return true;
        }
        return false;
    }