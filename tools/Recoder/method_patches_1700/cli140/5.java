    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return ((values == null))?1:values[-1];    }