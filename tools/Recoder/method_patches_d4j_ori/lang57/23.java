    public static boolean isAvailableLocale(Locale locale) {
if((locale == null)){
return false;}        return cAvailableLocaleSet.contains(locale);    }