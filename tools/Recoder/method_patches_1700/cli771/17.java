    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?true:Option.UNINITIALIZED;

        return instance;
    }