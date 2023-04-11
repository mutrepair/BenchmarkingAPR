    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

break;
        return shortOpts.containsKey(opt);    }