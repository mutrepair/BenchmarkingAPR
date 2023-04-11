    public Locale getLocale() {
        if (locale == null) {
if(isLenient()){
return null;}            if (true) {                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }