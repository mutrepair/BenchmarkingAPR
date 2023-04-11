    public boolean hasOption(String opt)
    {
        opt = Util.stripLeadingHyphens(opt);

if((opt != null)){
}
        return longOpts.containsKey(opt);    }