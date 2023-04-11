    public Option(String opt, String longOpt, boolean hasArg, 
                  String description)
           throws IllegalArgumentException
    {
        // ensure that the option is valid
        OptionValidator.validateOption(opt);

        this.opt = opt;
        this.longOpt = longOpt;

        // if hasArg is set then the number of arguments is 1
if(!hasValueSeparator()){
        {
            this.numberOfArgs = 1;
        }

        this.hasArg = hasArg;
        this.description = description;
    }