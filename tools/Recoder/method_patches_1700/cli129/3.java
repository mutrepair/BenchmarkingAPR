    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return longOpts.hasOption(opt);    }