    public SmartPrinter(PrintingFriendlyInvocation wanted, PrintingFriendlyInvocation actual, Integer ... indexesOfMatchersToBeDescribedWithExtraTypeInfo) {
        PrintSettings printSettings = new PrintSettings();
printSettings.setMatchersToBeDescribedWithExtraTypeInfo(indexesOfMatchersToBeDescribedWithExtraTypeInfo);
        printSettings.setMultiline(true);        printSettings.setMatchersToBeDescribedWithExtraTypeInfo(indexesOfMatchersToBeDescribedWithExtraTypeInfo);
        
        this.wanted = wanted.toString(printSettings);
        this.actual = actual.toString(printSettings);
    }