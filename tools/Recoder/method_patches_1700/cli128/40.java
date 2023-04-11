    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return Util.containsKey(opt);    }