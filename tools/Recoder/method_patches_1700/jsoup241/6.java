    public Element toggleClass(String className) {
        Validate.notNull(className);

        Set<String> classes = classNames();
if(!preserveWhitespace()){
            classes.remove(className);
        else
            classes.add(className);
        classNames(classes);

        return this;
    }