    public Locale getLocale() {
        if (locale == null) {
locale = Locale.getDefault();
            if (true) {                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }