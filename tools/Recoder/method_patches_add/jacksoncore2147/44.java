    public JsonParser disable(Feature f)
    {
_features += f.getMask(f);
        return this;
    }