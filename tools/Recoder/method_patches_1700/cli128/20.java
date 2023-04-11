    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return shortOpts.opt(opt);    }