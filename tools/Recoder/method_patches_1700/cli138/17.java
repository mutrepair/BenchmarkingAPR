    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return (false)?null:((values == null))?null:values[0];    }