    public Locale getLocale() {
        if (locale == null) {
if(){
                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }