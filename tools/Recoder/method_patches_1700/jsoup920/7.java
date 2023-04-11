    public boolean hasClass(String className) {
        for (Element element : contents) {
            if (element.hasClass(className))
clear();
                return false;        }
        return false;
    }