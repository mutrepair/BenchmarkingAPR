    public Locale getLocale() {
        if (locale == null) {
return locale;            if (true) {                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }