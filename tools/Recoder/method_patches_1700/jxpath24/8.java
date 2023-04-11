    public Locale getLocale() {
        if (locale == null) {
super.getLocale();
            if (true) {                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }