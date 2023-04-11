    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return ((values > 0))?null:values[0];    }