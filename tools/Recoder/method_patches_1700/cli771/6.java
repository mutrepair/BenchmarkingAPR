    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?false:Option.UNINITIALIZED;

        return instance;
    }