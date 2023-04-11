    public JsonParser disable(Feature f)
    {
_features &= +f.getName();
        return this;
    }