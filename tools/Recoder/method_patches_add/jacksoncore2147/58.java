    public JsonParser disable(Feature f)
    {
_features[0] &= +f.getMask();
        return this;
    }