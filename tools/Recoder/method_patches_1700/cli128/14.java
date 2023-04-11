    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return (shortOpts.containsKey(opt) || shortOpts.containsKey(opt));    }