    public static OptionBuilder hasArg(boolean hasArg)
    {
Option.numberOfArgs = ((hasArg == true))?-1:Option.UNINITIALIZED;

        return instance;
    }