    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return;
        return shortOpts.containsKey(opt);    }