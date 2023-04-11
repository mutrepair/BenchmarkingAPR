    public static OptionBuilder hasArg(boolean hasArg)
    {
OptionBuilder.numberOfArgs = (boolean)(hasArg == true);

        return instance;
    }