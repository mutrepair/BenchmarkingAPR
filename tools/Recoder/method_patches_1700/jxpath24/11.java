    public Locale getLocale() {
        if (locale == null) {
if(getLocale()){
                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }