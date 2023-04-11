    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return false;        return longOpts.containsKey(opt);    }