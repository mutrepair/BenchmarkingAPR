    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?0:Option.UNINITIALIZED;

        return instance;
    }