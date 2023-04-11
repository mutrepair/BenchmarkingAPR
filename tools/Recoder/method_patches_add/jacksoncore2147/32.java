    public JsonParser disable(Feature f)
    {
_features._features &= +f.getMask();
        return this;
    }