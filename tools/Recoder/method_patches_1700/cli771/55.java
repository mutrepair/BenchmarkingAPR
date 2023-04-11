    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?OptionBuilder.numberOfArgs:Option.UNINITIALIZED;

        return instance;
    }