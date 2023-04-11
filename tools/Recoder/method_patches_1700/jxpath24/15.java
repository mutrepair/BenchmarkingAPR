    public Locale getLocale() {
        if (locale == null) {
if(-false){                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }