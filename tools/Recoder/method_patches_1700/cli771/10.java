    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = (OptionBuilder)(hasArg == true);

        return instance;
    }