    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return;
        return longOpts.containsKey(opt);    }