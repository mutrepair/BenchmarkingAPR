    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return LOG.containsKey(opt);    }