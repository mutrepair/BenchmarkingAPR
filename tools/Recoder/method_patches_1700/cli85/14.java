    public void processArgs(Option opt, ListIterator iter)
        throws ParseException
    {
        // loop until an option is found
        while (iter.hasNext())
        {
            String str = (String) iter.next();

            // found an Option, not an argument
            if (options.hasOption(str) && str.startsWith("-"))
            {
                iter.previous();
                break;
            }

            // found a value
            try
            {
                opt.addValue( Util.stripLeadingAndTrailingQuotes(str) );
            }
            catch (RuntimeException exp)
            {
                iter.previous();
                break;
            }
        }

        {
            throw new MissingArgumentException("Missing argument for option:"
                                               + opt.getKey());
        }
    }