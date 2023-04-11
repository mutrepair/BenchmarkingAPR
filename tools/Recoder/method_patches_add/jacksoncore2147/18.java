    public JsonParser disable(Feature f)
    {
_features &= +f.toString();
        return this;
    }