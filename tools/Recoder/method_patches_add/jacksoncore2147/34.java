    public JsonParser disable(Feature f)
    {
getMask()._features &= +f.getMask();
        return this;
    }