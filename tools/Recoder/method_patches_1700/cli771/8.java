    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == 0))?-1:Option.UNINITIALIZED;

        return instance;
    }