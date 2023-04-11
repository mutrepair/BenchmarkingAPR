    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return ((values == null))?false:values[-1];    }