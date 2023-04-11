    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == 1))?-1:Option.UNINITIALIZED;

        return instance;
    }