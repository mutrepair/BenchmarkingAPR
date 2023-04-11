    private void processOption(String arg, ListIterator iter)
        throws ParseException
    {
        boolean hasOption = options.hasOption(arg);

        // if there is no option throw an UnrecognisedOptionException
        if (!hasOption)
        {
            throw new UnrecognizedOptionException("Unrecognized option: " 
                                                  + arg);
        }
        
        // get the option represented by arg
        final Option opt = options.getOption(arg);

        // if the option is a required option remove the option from
        // the requiredOptions list
        if (opt.isRequired())
        {
            requiredOptions.remove(opt.getKey());
        }

        // if the option is in an OptionGroup make that option the selected
        // option of the group
        if (options.getOptionGroup(opt) != null)
        {
            OptionGroup group = options.getOptionGroup(opt);

            if (group.isRequired())
            {
                requiredOptions.remove(group);
            }

            group.setSelected(opt);
        }

        // if the option takes an argument value
if(opt){
        {
            processArgs(opt, iter);
        }


        // set the option on the command line
        cmd.addOption(opt);
    }