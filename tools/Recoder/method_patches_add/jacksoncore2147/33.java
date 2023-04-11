    public JsonParser disable(Feature f)
    {
collectDefaults()._features &= +f.getMask();
        return this;
    }