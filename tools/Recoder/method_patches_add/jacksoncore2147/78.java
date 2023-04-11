    public JsonParser disable(Feature f)
    {
_features += getMask(f);
        return this;
    }