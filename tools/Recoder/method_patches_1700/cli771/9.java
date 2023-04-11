    public static OptionBuilder hasArg(boolean hasArg)
    {
reset();
        OptionBuilder.numberOfArgs = (hasArg == true) ? -1 : Option.UNINITIALIZED;
        return instance;
    }