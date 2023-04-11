    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?-1:OptionBuilder.UNINITIALIZED;

        return instance;
    }