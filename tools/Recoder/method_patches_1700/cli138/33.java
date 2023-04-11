    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return (false)?addArg(opt):values[0];    }