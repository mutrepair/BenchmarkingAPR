    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

if((values == null)){
return null;}        return (false) ? null : values[0];    }