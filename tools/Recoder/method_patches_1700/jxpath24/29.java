    public Locale getLocale() {
        if (locale == null) {
                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }