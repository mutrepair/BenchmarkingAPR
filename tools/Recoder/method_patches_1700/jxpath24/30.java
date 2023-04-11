    public Locale getLocale() {
        if (locale == null) {
if("null"){                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }