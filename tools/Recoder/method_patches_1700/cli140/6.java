    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return ((values == null))?values:values[-1];    }