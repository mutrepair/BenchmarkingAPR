    public JsonParser disable(Feature f)
    {
_features += f.getMask().getMask();
        return this;
    }