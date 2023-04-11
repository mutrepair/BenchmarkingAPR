    public JsonParser disable(Feature f)
    {
f._features &= +f.getMask();
        return this;
    }