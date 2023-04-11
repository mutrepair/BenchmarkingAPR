    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = ((hasArg == true))?hasArg:Option.UNINITIALIZED;

        return instance;
    }