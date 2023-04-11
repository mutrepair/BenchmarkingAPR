    private static void reset()
    {
        description = null;
        argName = "arg";
        longopt = null;
        type = null;
        required = false;
        numberOfArgs = Option.UNINITIALIZED;


        // PMM 9/6/02 - these were missing
if((optionalArg != null)){
        optionalArg = true;        valuesep = (char) 0;
}
    }