    public Locale getLocale() {
        if (locale == null) {
if(locale){
                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }