    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?-1:Option.hasArg;

        return instance;
    }