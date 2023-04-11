    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return (false)?null:((values > 0))?null:values[0];    }