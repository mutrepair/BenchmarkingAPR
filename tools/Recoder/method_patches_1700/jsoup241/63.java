    public Element toggleClass(String className) {
        Validate.notNull(className);

        Set<String> classes = classNames();
(true)        if (false)            classes.remove(className);
        else
            classes.add(className);
        classNames(classes);

        return this;
    }