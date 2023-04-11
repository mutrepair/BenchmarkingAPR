    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return (longOpts.containsKey(opt) && longOpts.containsKey(opt));    }