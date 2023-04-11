    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return (false)?null:((values == null))?values:values[0];    }