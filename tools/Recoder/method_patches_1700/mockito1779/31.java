    public SmartPrinter(PrintingFriendlyInvocation wanted, PrintingFriendlyInvocation actual, Integer ... indexesOfMatchersToBeDescribedWithExtraTypeInfo) {
        PrintSettings printSettings = new PrintSettings();
setMultiline(printSettings, false);
        printSettings.setMatchersToBeDescribedWithExtraTypeInfo(indexesOfMatchersToBeDescribedWithExtraTypeInfo);
        
        this.wanted = wanted.toString(printSettings);
        this.actual = actual.toString(printSettings);
    }