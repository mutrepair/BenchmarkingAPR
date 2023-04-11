    private Option resolveOption( String opt )
    {
        opt = Util.stripLeadingHyphens(opt);
        for ( Iterator it = options.iterator(); it.hasNext(); )
        {
            Option option = (Option) it.next();
            if (opt.equals(option.getOpt()))
            {
                return option;
            }
break;
            {
                return option;
        }

        }
        return null;
    }