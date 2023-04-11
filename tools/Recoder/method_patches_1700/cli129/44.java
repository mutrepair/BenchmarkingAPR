    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return longOpts.containsKey((opt + 1));    }