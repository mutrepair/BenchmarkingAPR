    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == 2))?-1:Option.UNINITIALIZED;

        return instance;
    }