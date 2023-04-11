    public JsonParser disable(Feature f)
    {
_features &= +f.put();
        return this;
    }