    public Locale getLocale() {
        if (locale == null) {
if(Locale.isLenient()){
                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }