    public JsonParser disable(Feature f)
    {
f &= +f.getMask();
        return this;
    }