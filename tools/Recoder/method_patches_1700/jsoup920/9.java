    public boolean hasClass(String className) {
        for (Element element : contents) {
            if (element.hasClass(className))
return;
        }
        return false;
    }