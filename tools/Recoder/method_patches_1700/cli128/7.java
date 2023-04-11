    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return ((opt != null) && shortOpts.containsKey(opt));    }