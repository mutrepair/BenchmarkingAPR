    public JsonParser disable(Feature f)
    {
_features &= +f.add();
        return this;
    }