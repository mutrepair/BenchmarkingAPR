    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?OptionBuilder.UNINITIALIZED:Option.UNINITIALIZED;

        return instance;
    }