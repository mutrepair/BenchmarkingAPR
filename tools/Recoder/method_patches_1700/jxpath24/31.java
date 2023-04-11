    public Locale getLocale() {
        if (locale == null) {
locale = Locale.getDefault();
                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }