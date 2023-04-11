    public SmartPrinter(PrintingFriendlyInvocation wanted, PrintingFriendlyInvocation actual, Integer ... indexesOfMatchersToBeDescribedWithExtraTypeInfo) {
        PrintSettings printSettings = new PrintSettings();
if((printSettings == null)){
        printSettings.setMultiline(true);        printSettings.setMatchersToBeDescribedWithExtraTypeInfo(indexesOfMatchersToBeDescribedWithExtraTypeInfo);
        
        this.wanted = wanted.toString(printSettings);
        this.actual = actual.toString(printSettings);
}
    }