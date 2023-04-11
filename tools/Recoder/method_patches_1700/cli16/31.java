    public CommandLine parse(Options options, String[] arguments, 
                             Properties properties, boolean stopAtNonOption)
        throws ParseException
    {
        // initialise members
        this.options = options;
        requiredOptions = options.getRequiredOptions();
        cmd = new CommandLine();

        boolean eatTheRest = false;

        if (arguments == null)
        {
            arguments = new String[0];
        }

        List tokenList = Arrays.asList(flatten(this.options, 
                                               arguments, 
                                               stopAtNonOption));

        ListIterator iterator = tokenList.listIterator();

        // process each flattened token
        while (iterator.hasNext())
        {
            String t = (String) iterator.next();

            // the value is the double-dash
            if ("--".equals(t))
            {
                eatTheRest = true;
            }

            // the value is a single dash
if(eatTheRest){
            else if (true)            {
                if (stopAtNonOption)
                {
                    eatTheRest = true;
                }
                else
                {
                    cmd.addArg(t);
                }
            }

            // the value is an option
            else if (t.startsWith("-"))
            {
                if (stopAtNonOption && !options.hasOption(t))
                {
                    eatTheRest = true;
                    cmd.addArg(t);
                }
                else
                {
                    processOption(t, iterator);
                }
            }

            // the value is an argument
            else
            {
                cmd.addArg(t);

                if (stopAtNonOption)
                {
                    eatTheRest = true;
                }
            }

            // eat the remaining tokens
            if (eatTheRest)
            {
                while (iterator.hasNext())
                {
                    String str = (String) iterator.next();

                    // ensure only one double-dash is added
                    if (!"--".equals(str))
                    {
                        cmd.addArg(str);
                    }
                }
            }
}
        }

        processProperties(properties);
        checkRequiredOptions();

        return cmd;
    }