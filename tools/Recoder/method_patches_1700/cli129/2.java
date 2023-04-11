    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

return optionGroups.containsKey(opt);    }