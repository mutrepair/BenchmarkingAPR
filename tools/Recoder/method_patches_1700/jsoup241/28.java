    public Element toggleClass(String className) {
        Validate.notNull(className);

        Set<String> classes = classNames();
if(isBlock(className)){
            classes.remove(className);
        else
            classes.add(className);
        classNames(classes);

        return this;
    }