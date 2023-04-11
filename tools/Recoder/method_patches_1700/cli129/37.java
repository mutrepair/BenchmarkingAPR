    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

opt = Util.stripLeadingHyphens(opt);
        return longOpts.containsKey(opt);    }