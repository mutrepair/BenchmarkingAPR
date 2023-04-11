    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

if((opt == null)){
return false;}        return shortOpts.containsKey(opt);    }