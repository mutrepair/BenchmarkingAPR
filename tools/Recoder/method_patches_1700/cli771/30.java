    public static OptionBuilder hasArg(boolean hasArg)
    {
numberOfArgs = ((hasArg == true))?-1:Option.UNINITIALIZED;

        return instance;
    }