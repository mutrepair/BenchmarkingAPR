    public Locale getLocale() {
        if (locale == null) {
if(parentContext.getLocale()){
                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }