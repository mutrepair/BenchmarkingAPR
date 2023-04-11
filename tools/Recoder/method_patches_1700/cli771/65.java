    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?-1:((hasArg == true))?hasArg:Option.UNINITIALIZED;

        return instance;
    }