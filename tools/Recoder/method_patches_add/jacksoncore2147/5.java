    public JsonParser disable(Feature f)
    {
_features &= +f.getMask();
        return this;
    }