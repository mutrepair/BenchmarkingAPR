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
if(it.next()){
            {
                return option;
        }

        }
        return null;
    }