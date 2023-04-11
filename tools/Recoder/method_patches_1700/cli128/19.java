    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return false;        return shortOpts.containsKey(opt);    }