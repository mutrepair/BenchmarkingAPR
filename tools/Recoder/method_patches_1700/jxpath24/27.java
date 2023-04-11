    public Locale getLocale() {
        if (locale == null) {
return parentContext.getLocale();            if (true) {                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }