    public JsonParser disable(Feature f)
    {
_features &= +f.get();
        return this;
    }