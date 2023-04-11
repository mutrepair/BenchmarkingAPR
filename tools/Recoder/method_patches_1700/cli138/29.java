    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return (values.isEmpty())?null:values[0];    }