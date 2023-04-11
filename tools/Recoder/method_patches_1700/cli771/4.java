    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = (hasArg == true);

        return instance;
    }