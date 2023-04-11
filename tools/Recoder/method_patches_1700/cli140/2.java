    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return ((values == null))?0:values[-1];    }