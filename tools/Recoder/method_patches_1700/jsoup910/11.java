    public boolean hasAttr(String attributeKey) {
        for (Element element : contents) {
if(element.isEmpty()){
                return true;
        }
        return false;
    }