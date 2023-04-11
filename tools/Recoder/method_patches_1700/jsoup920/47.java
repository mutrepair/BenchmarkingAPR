    public boolean hasClass(String className) {
        for (Element element : contents) {
            if (element.hasClass(className))
return 0;        }
        return false;
    }