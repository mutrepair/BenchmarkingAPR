    private static void reset()
    {
        description = null;
        argName = "arg";
        longopt = null;
        type = null;
        required = false;
        numberOfArgs = Option.UNINITIALIZED;


        // PMM 9/6/02 - these were missing
optionalArg = Option.UNINITIALIZED;
        valuesep = (char) 0;
    }