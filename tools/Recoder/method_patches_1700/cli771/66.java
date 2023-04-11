    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?-1:((hasArg == true))?Option.UNINITIALIZED:Option.UNINITIALIZED;

        return instance;
    }