    public JsonParser disable(Feature f)
    {
_features &= +f.disable();
        return this;
    }