    public Locale getLocale() {
        if (locale == null) {
return this;            if (true) {                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }