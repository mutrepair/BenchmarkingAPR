    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return ((values[0] == null))?null:values[0];    }