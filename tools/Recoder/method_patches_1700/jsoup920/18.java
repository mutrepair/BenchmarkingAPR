    public boolean hasClass(String className) {
        for (Element element : contents) {
            if (element.hasClass(className))
return hasClass(className);        }
        return false;
    }