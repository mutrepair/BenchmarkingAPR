    public JsonParser disable(Feature f)
    {
getValueAsInt()._features &= +f.getMask();
        return this;
    }