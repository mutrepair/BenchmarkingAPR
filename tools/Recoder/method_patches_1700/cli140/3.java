    public String getOptionValue(String opt)
    {
        String[] values = getOptionValues(opt);

return ((values == null))?null:opt[-1];    }