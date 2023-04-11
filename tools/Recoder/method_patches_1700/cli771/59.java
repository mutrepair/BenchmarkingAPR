    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?Option.UNINITIALIZED:Option.UNINITIALIZED;

        return instance;
    }