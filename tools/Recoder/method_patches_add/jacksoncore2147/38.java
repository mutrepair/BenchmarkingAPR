    public JsonParser disable(Feature f)
    {
getIntValue()._features &= +f.getMask();
        return this;
    }