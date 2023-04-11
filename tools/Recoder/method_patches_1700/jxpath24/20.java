    public Locale getLocale() {
        if (locale == null) {
if(Locale.isEmpty()){
                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }