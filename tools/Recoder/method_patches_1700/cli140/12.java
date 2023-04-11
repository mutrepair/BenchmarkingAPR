    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return ((values == null))?2:values[-1];    }