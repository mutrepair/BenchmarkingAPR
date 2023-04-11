    public boolean hasClass(String className) {
        for (Element element : contents) {
            if (element.hasClass(className))
contents = element;
                return false;        }
        return false;
    }