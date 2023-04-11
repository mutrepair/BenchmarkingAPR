    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return (false)?addArg(values):values[0];    }