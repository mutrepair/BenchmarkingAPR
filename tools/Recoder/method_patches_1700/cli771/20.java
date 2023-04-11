    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = (OptionBuilder)OptionBuilder.numberOfArgs = ((hasArg == true))?-1:Option.UNINITIALIZED;

        return instance;
    }