    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?-1:OptionBuilder.numberOfArgs;

        return instance;
    }