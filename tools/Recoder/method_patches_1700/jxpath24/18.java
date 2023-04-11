    public Locale getLocale() {
        if (locale == null) {
if(-1){                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }