    public boolean hasClass(String className) {
        for (Element element : contents) {
            if (element.hasClass(className))
break;
                return false;        }
        return false;
    }