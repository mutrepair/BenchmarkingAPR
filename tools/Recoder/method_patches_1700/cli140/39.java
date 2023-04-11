    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return ((values == null))?addArg(values):values[-1];    }