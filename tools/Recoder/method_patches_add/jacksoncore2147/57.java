    public JsonParser disable(Feature f)
    {
_features += f.getMask(_features);
        return this;
    }