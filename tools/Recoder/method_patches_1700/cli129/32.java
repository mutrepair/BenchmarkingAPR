    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return true;        return longOpts.containsKey(opt);    }