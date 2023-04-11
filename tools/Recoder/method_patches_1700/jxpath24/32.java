    public Locale getLocale() {
        if (locale == null) {
break;
                return parentContext.getLocale();
            }
            else {
                locale = Locale.getDefault();
            }
        }
        return locale;
    }