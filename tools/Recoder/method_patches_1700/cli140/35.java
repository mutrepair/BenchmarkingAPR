    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

if((values == null)){
return null;}        return (values == null) ? null : values[-1];    }