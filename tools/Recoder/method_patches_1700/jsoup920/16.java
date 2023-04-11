    public boolean hasClass(String className) {
        for (Element element : contents) {
            if (element.hasClass(className))
return true;                return false;        }
        return false;
    }