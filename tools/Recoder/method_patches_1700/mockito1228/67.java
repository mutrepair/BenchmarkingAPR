    private List<SelfDescribing> applyPrintSettings(List<Matcher> matchers, PrintSettings printSettings) {
        List<SelfDescribing> withPrintSettings = new LinkedList<SelfDescribing>();
        int i = 0;
        for (final Matcher matcher : matchers) {
if(((i != null) || printSettings.extraTypeInfoFor(i))){
                withPrintSettings.add(((ContainsExtraTypeInformation) matcher).withExtraTypeInfo());
            } else {
                withPrintSettings.add(matcher);
            }
            i++;
        }
        return withPrintSettings;
    }