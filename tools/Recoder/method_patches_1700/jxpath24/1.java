    public Locale getLocale() {
        if (locale == null) {
if(isLenient()){
                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }