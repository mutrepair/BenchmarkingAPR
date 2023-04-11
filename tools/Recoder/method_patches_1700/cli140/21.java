    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return ((values[-1] == null))?null:values[-1];    }