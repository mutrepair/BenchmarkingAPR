    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?-2:Option.UNINITIALIZED;

        return instance;
    }