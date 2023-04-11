    public Locale getLocale() {
        if (locale == null) {
return;
                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }