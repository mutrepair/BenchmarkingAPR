    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?null:Option.UNINITIALIZED;

        return instance;
    }