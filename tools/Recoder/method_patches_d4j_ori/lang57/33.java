    public static boolean isAvailableLocale(Locale locale) {
if((locale == null)){
return true;}        return cAvailableLocaleSet.contains(locale);    }